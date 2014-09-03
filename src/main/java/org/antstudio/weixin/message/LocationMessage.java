package org.antstudio.weixin.message;

import org.antstudio.weixin.annotation.CDATAIgnore;

/**
 * 地理位置消息
 * @author Gavin
 * @date 2014-09-03
 */
public class LocationMessage extends BaseMessage {

    @CDATAIgnore
    private String location_X;

    @CDATAIgnore
    private String location_Y;

    @CDATAIgnore
    private String scale;

    private String label;

    private String msgId;


    public String getLocation_X() {
        return location_X;
    }

    public void setLocation_X(String location_X) {
        this.location_X = location_X;
    }

    public String getLocation_Y() {
        return location_Y;
    }

    public void setLocation_Y(String location_Y) {
        this.location_Y = location_Y;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
