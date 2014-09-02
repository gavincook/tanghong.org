package org.antstudio.weixin.message;

import org.antstudio.weixin.annotation.CDATAIgnore;

/**
 * 图片消息
 * @author Gavin
 * @date 9/2/2014
 */
public class ImageMessage extends BaseMessage {

    private String picUrl;

    private String mediaId;

    @CDATAIgnore
    private String msgId;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
