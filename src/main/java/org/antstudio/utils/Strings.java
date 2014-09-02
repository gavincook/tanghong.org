package org.antstudio.utils;

/**
 * Created by Gavin on 9/2/2014.
 */
public class Strings {

    public static String upperFirst(String src){
        if(src == null){
            return "";
        }else{
            return src.substring(0,1).toUpperCase()+src.substring(1);
        }
    }

}
