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
            EVENT = "Event",
            LOCATION_X = "Location_X",
            LOCATION_Y = "Location_Y",
            LABEL = "Label",
            SCALE = "Scale";

    private static MessageFactory messageFactory = new MessageFactory();

    private MessageFactory(){};

    public static MessageFactory newInstance(){
        return messageFactory;
    }

    /**
     * 从参数map中获取消息对象
     * @param map
     * @return
     */
    public  Message getMessage(Map<String,String> map){
        MsgType msgType = MsgType.valueOf(map.get(MSGTYPE).toUpperCase());
        switch(msgType){
            case TEXT : return toTextMessage(map);
            case IMAGE: return toImageMessage(map);
            case EVENT: return toBaseEventMessage(map);
            case LOCATION:return toLocationMessage(map);
        }
        return null;
    }


    /**
     * 获取文本消息
     * @param map
     * @return
     */
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

    /**
     * 获取图片消息
     * @param map
     * @return
     */
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

    /**
     * 获取基础事件消息(订阅和取消订阅)
     * @param map
     * @return
     */
    public BaseEvent toBaseEventMessage(Map<String,String> map){
        BaseEvent event = new BaseEvent();
        event.setCreateTime(parseDate(map.get(CREATETIME)));
        event.setMsgType(MsgType.TEXT);
        event.setFromUserName(map.get(FROMUSERNAME));
        event.setToUserName(map.get(TOUSERNAME));
        event.setEvent(map.get(EVENT));
        return event;
    }

    /**
     * 位置信息消息
     * @param map
     * @return
     */
    public LocationMessage toLocationMessage(Map<String,String> map){
        LocationMessage lm = new LocationMessage();
        lm.setCreateTime(parseDate(map.get(CREATETIME)));
        lm.setMsgType(MsgType.LOCATION);
        lm.setFromUserName(map.get(FROMUSERNAME));
        lm.setToUserName(map.get(TOUSERNAME));
        lm.setMsgId(MSGID);
        lm.setLocation_X(map.get(LOCATION_X));
        lm.setLocation_Y(map.get(LOCATION_Y));
        lm.setLabel(map.get(LABEL));
        lm.setScale(map.get(SCALE));
        return lm;
    }

    private Date parseDate(String dateString){
        return new Date(Long.parseLong(dateString));
    }


}
