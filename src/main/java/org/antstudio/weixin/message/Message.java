package org.antstudio.weixin.message;

/**
 * 消息接口
 * @author Gavin
 * @date 9/2/2014
 */
public interface Message {

    /**
     * 公用的四个属性
     */
    public static String MSGTYPE = "MsgType",
            TOUSERNAME = "ToUserName",
            FROMUSERNAME = "FromUserName",
            CREATETIME = "CreateTime";

    public MsgType getMsgType();

    public String toXml();
}
