package org.antstudio.weixin.message;

import org.antstudio.utils.Strings;
import org.antstudio.weixin.annotation.CDATAIgnore;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * 微信消息
 * @author Gavin
 * @date 9/2/2014
 */
public abstract class BaseMessage implements Message{

    protected String toUserName;

    protected String fromUserName;

    protected Date createTime;

    protected MsgType msgType;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    @Override
    public MsgType getMsgType() {
        return msgType;
    }

    @Override
    public String toXml(){
        StringBuilder sb = new StringBuilder("<xml>");
        sb.append(wrapName(FROMUSERNAME,false)).append(wrapValue(getFromUserName())).append(wrapName(FROMUSERNAME,true));
        sb.append(wrapName(TOUSERNAME,false)).append(wrapValue(getToUserName())).append(wrapName(TOUSERNAME,true));
        sb.append(wrapName(MSGTYPE,false)).append(wrapValue(getMsgType().toString())).append(wrapName(MSGTYPE,true));
        sb.append(wrapName(CREATETIME,false)).append(getCreateTime().getTime()).append(wrapName(CREATETIME, true));
        sb.append(toSubXml());
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 子类的xml化，可覆盖。默认读取每个字段
     * @return
     */
    protected String toSubXml(){
        StringBuilder sb = new StringBuilder();
        try {
            for (Field f : this.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                sb.append(wrapName(Strings.upperFirst(f.getName()),false));
                if(f.isAnnotationPresent(CDATAIgnore.class)){
                    sb.append((String)f.get(this));
                }else{
                    sb.append(wrapValue((String)f.get(this)));
                }
                sb.append(wrapName(Strings.upperFirst(f.getName()), true));
            }
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    protected String wrapName(String name,boolean end){
        if(end){
            return "</"+name+">";
        }
        return "<"+name+">";
    }

    protected String wrapValue(String value){
        if(StringUtils.isEmpty(value)){
            return "";
        } else {
            return "<![CDATA["+value+"]]>";
        }
    }
}
