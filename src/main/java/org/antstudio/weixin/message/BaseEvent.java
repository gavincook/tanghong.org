package org.antstudio.weixin.message;

/**
 * 事件消息
 * Created by Gavin on 9/2/2014.
 */
public class BaseEvent extends BaseMessage {

    private String event;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

}
