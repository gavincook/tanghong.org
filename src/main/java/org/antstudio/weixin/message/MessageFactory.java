package org.antstudio.weixin.message;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * 用于构造消息对象的工厂
 * @author Gavin
 * @date 9/2/2014
 */
public class MessageFactory {

    private static String CONTENT = "Content",
            TOUSERNAME = "ToUserName",
            FROMUSERNAME = "FromUserName",
            CREATETIME = "CreateTime",
            MSGTYPE = "MsgType",
            MSGID = "MsgId";

    private static MessageFactory messageFactory = new MessageFactory();

    private MessageFactory(){};

    public static MessageFactory newInstance(){
        return messageFactory;
    }

    public  Message getMessage(Map<String,String> map){
        MsgType msgType = MsgType.valueOf(map.get(MSGTYPE).toUpperCase());
        switch(msgType){
            case TEXT : return toTextMessage(map);
        }
        return null;
    }


    public TextMessage toTextMessage(Map<String,String> map){
        TextMessage tm = new TextMessage();
        tm.setContent(map.get(CONTENT));
        tm.setMsgId(map.get(MSGID));
        tm.setCreateTime(parseDate(map.get(CREATETIME)));
        tm.setMsgType(MsgType.TEXT);
        tm.setFromUserName(map.get(FROMUSERNAME));
        tm.setToUserName(map.get(TOUSERNAME));
        return tm;
    }

    private Date parseDate(String dateString){
        return new Date(Long.parseLong(dateString));
    }


}
