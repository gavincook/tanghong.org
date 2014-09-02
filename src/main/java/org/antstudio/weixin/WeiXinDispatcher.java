package org.antstudio.weixin;


import org.antstudio.weixin.message.Message;
import org.antstudio.weixin.message.MessageFactory;
import org.antstudio.weixin.message.MsgType;
import org.antstudio.weixin.message.TextMessage;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.InputSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.Date;
import java.util.Map;

/**
 * Created by Gavin on 9/1/2014.
 */
@Controller
public class WeiXinDispatcher {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/weixin")
    @ResponseBody
    public String dispatch(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map m = request.getParameterMap();

        if(request.getMethod().equalsIgnoreCase("get")){
            return request.getParameter("echostr");
        }else{
            Message message = getContentFromRequest(request);

            if(message instanceof TextMessage){
                TextMessage tm = (TextMessage)message;

                TextMessage rtm = new TextMessage();
                rtm.setMsgId(tm.getMsgId());
                rtm.setFromUserName(tm.getToUserName());
                rtm.setToUserName(tm.getFromUserName());
                rtm.setCreateTime(new Date());
                rtm.setContent(tm.getContent());
                rtm.setMsgType(MsgType.TEXT);
                return rtm.toXml();
            }
            return "";
        }
    }


    /**
     * 从请求输入流中获取消息对象
     * @param request
     * @return
     */
    private  Message getContentFromRequest(HttpServletRequest request){
        try(InputStream in = request.getInputStream();
           ){
            InputSource is = new InputSource(in);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            XmlHandler xmlHandler = new XmlHandler();
            saxParser.parse(is,xmlHandler);
            logger.debug("----------接收到的参数-----------------------");
            for(Map.Entry entry:xmlHandler.getValuesMap().entrySet()){
                logger.debug(entry.getKey() + "=" + entry.getValue());
            }
            logger.debug("----------接收到的参数-----------------------");

            Message message = MessageFactory.newInstance().getMessage(xmlHandler.getValuesMap());
            return message;
        }catch (Exception e){
            throw new IllegalStateException(e.getCause());
        }
    }

}
