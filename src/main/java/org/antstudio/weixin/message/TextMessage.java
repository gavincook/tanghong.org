package org.antstudio.weixin.message;


import org.antstudio.weixin.annotation.CDATAIgnore;

/**
 * 文本消息
 * @author Gavin
 * @date 9/2/2014
 */
public class TextMessage extends BaseMessage {

    private String content;

    @CDATAIgnore
    private String msgId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
