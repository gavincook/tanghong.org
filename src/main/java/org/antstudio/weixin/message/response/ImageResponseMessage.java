package org.antstudio.weixin.message.response;

import org.antstudio.weixin.message.BaseMessage;

/**
 * 响应图片消息
 * @author Gavin
 * @date 9/2/2014
 */
public class ImageResponseMessage extends BaseMessage{

    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    protected String toSubXml() {
       StringBuilder sb = new StringBuilder();
        sb.append("<Image> <MediaId>")
          .append("<![CDATA[").append(mediaId).append("]]>")
          .append("</MediaId> </Image>");
        return sb.toString();
    }
}
