package org.antstudio.weixin;


import org.antstudio.weixin.message.*;
import org.antstudio.weixin.message.response.ImageResponseMessage;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.InputSource;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.Date;
import java.util.Map;

/**
 * 微信消息处理控制器
 * @author Gavin
 * @date 9/2/2014
 */
@Controller
public class WeiXinDispatcher {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/weixin")
    @ResponseBody
    public String dispatch(HttpServletRequest request) {
        if(request.getMethod().equalsIgnoreCase("get")){
            return request.getParameter("echostr");//原样返回echostr
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
            }else if(message instanceof ImageMessage){
                ImageMessage im = (ImageMessage)message;

                ImageResponseMessage irm = new ImageResponseMessage();
                irm.setMediaId(im.getMediaId());
                irm.setFromUserName(im.getToUserName());
                irm.setToUserName(im.getFromUserName());
                irm.setMsgType(MsgType.IMAGE);
                irm.setCreateTime(new Date());
                return irm.toXml();
            } else if(message instanceof BaseEvent){
                BaseEvent event = (BaseEvent)message;
                switch (event.getEvent()){
                    case "subscribe":
                        TextMessage rtm = new TextMessage();
                        rtm.setFromUserName(event.getToUserName());
                        rtm.setToUserName(event.getFromUserName());
                        rtm.setCreateTime(new Date());
                        rtm.setContent("终于,你还是关注了我,对于此我只能说\"~干的漂亮~\"");
                        rtm.setMsgType(MsgType.TEXT);
                        return rtm.toXml();
                    case "unsubscribe":return "";
                }
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
