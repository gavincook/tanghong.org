package org.antstudio.weixin.message;

/**
 * 消息类型
 * @author Gavin
 * @date 9/2/2014
 */
public enum MsgType {
    TEXT("text"),//文本消息
    IMAGE("image"),//图片消息
    VOICE("voice"),//语音消息
    VIDEO("video"),//视频消息
    LOCATION("location"),//地理位置消息
    LINK("link"),//链接消息
    EVENT("event");//事件消息

    private String value;

    private MsgType(String value){
        this.value = value;
    }

    public String toString(){
        return this.value;
    }
}
