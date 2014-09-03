package org.antstudio.weixin.message;

/**
 * 链接消息
 * @author:Gavin
 * @date 9/3/2014
 */
public class LinkMessage extends BaseMessage{

    private String title;

    private String description;

    private String url;

    private String msgId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
