package org.antstudio.weixin.annotation;

import java.lang.annotation.*;

/**
 * 是否忽略<![CDATA[xxx]]>
 * Created by Gavin on 9/2/2014.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CDATAIgnore {
}
