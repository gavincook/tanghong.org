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
            MSGID = "MsgId",
            PICURL = "PicUrl",
            MEDIAID = "MediaId",
            EVENT = "Event";

    private static MessageFactory messageFactory = new MessageFactory();

    private MessageFactory(){};

    public static MessageFactory newInstance(){
        return messageFactory;
    }

    public  Message getMessage(Map<String,String> map){
        MsgType msgType = MsgType.valueOf(map.get(MSGTYPE).toUpperCase());
        switch(msgType){
            case TEXT : return toTextMessage(map);
            case IMAGE: return toImageMessage(map);
            case EVENT: return toBaseEventMessage(map);
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

    public ImageMessage toImageMessage(Map<String,String> map){
        ImageMessage im = new ImageMessage();
        im.setPicUrl(map.get(PICURL));
        im.setMediaId(map.get(MEDIAID));
        im.setMsgId(map.get(MSGID));
        im.setCreateTime(parseDate(map.get(CREATETIME)));
        im.setMsgType(MsgType.TEXT);
        im.setFromUserName(map.get(FROMUSERNAME));
        im.setToUserName(map.get(TOUSERNAME));
        return im;
    }

    public BaseEvent toBaseEventMessage(Map<String,String> map){
        BaseEvent event = new BaseEvent();
        event.setCreateTime(parseDate(map.get(CREATETIME)));
        event.setMsgType(MsgType.TEXT);
        event.setFromUserName(map.get(FROMUSERNAME));
        event.setToUserName(map.get(TOUSERNAME));
        event.setEvent(map.get(EVENT));
        return event;
    }

    private Date parseDate(String dateString){
        return new Date(Long.parseLong(dateString));
    }


}
