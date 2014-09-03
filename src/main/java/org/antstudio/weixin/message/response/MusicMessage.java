package org.antstudio.weixin.message.response;

import org.antstudio.weixin.message.BaseMessage;
import org.antstudio.weixin.message.MsgType;

/**
 * 音乐消息
 * @author:Gavin
 * @date 9/3/2014
 */
public class MusicMessage extends BaseMessage{

    private String title;
    private String description;
    private String musicURL;
    private String hQMusicUrl;
    private String thumbMediaId;

    public MusicMessage(){
        setMsgType(MsgType.MUSIC);
    }

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

    public String getMusicURL() {
        return musicURL;
    }

    public void setMusicURL(String musicURL) {
        this.musicURL = musicURL;
    }

    public String gethQMusicUrl() {
        return hQMusicUrl;
    }

    public void sethQMusicUrl(String hQMusicUrl) {
        this.hQMusicUrl = hQMusicUrl;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    @Override
    protected String toSubXml() {
        StringBuilder sb = new StringBuilder(wrapName("Music",false));
        sb.append(wrapName("Title",false)).append(wrapValue(title)).append(wrapName("Title",true));
        sb.append(wrapName("Description",false)).append(wrapValue(description)).append(wrapName("Description",true));
        sb.append(wrapName("MusicUrl",false)).append(wrapValue(musicURL)).append(wrapName("MusicUrl",true));
        sb.append(wrapName("HQMusicUrl",false)).append(wrapValue(hQMusicUrl)).append(wrapName("HQMusicUrl",true));
        sb.append(wrapName("ThumbMediaId",false)).append(wrapValue(thumbMediaId)).append(wrapName("ThumbMediaId",true));
        sb.append(wrapName("Music",true));
        return sb.toString();
    }
}
