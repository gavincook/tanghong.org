package org.antstudio.weixin.service;

import org.antstudio.weixin.XmlHandler;
import org.antstudio.weixin.message.TextMessage;
import org.antstudio.weixin.message.response.MusicMessage;
import org.antstudio.weixin.service.xml.BaiduMusicXmlHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;

/**
 * 音乐服务,用于根据歌曲名返回相应的音乐信息
 * @author:Gavin
 * @date 9/3/2014
 */
@Component
public class MusicService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static String BaiduMusicAPiUrl = "http://box.zhangmen.baidu.com/x?op=12&count=1&title=%s$$";
    public  MusicMessage getMusicMessage(TextMessage textMessage){
        String musicName = "";
        try{
            String content = textMessage.getContent();
            if(content.startsWith(",")||content.startsWith("，")){
                musicName = content.substring(1);
            }else if(content.startsWith("歌曲")){
                musicName = content.substring(2);
            }
            URL url = new URL(String.format(BaiduMusicAPiUrl,URLEncoder.encode(musicName,"UTF-8")));
            URLConnection urlConnection = url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputSource is = new InputSource(in);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            BaiduMusicXmlHandler baiduMusicXmlHandler = new BaiduMusicXmlHandler();
            saxParser.parse(is,baiduMusicXmlHandler);
            String musicUrl = baiduMusicXmlHandler.getMusicUrl();
            in.close();

            MusicMessage musicMessage = new MusicMessage();
            musicMessage.setFromUserName(textMessage.getToUserName());
            musicMessage.setToUserName(textMessage.getFromUserName());
            musicMessage.setMusicURL(musicUrl);
            musicMessage.sethQMusicUrl(musicUrl);
            musicMessage.setThumbMediaId("qadaNYwTd2Okn6Skla0xJB47HHaRlG-hBF_JIE3coqk");
            musicMessage.setTitle(musicName);
            musicMessage.setDescription(musicName);
            musicMessage.setCreateTime(new Date());
            return musicMessage;
        } catch (Exception e){
            logger.debug(e.getLocalizedMessage());
            logger.debug("将返回空的音乐信息");
        }
        return null;
    }


//%E7%83%9F%E7%81%AB%E7%9A%84%E5%85%89%E8%8A%92

}
