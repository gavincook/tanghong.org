package org.antstudio.weixin.message;

/**
 * Created by Gavin on 9/2/2014.
 */
public interface Message {

    public static String MSGTYPE = "MsgType",
            TOUSERNAME = "ToUserName",
            FROMUSERNAME = "FromUserName",
            CREATETIME = "CreateTime";
    public MsgType getMsgType();

    public String toXml();
}
