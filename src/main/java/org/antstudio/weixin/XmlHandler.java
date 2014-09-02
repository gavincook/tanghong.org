package org.antstudio.weixin;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于解析xml
 * @author Gavin
 * @date 9/2/2014
 */
public class XmlHandler extends DefaultHandler {

    private String currentTag;
    private Map<String,String> valuesMap = new HashMap<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        this.currentTag = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        this.currentTag = null;//因为标记之间空白和换行sax也会解析，并调用characters，因此需要处理完毕后将tag置为null,否则读取空格换行时会重复设置前一个tag
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(currentTag != null){
            valuesMap.put(currentTag,new String(ch,start,length));
        }
    }

    public Map<String,String> getValuesMap(){
        return this.valuesMap;
    }
}
