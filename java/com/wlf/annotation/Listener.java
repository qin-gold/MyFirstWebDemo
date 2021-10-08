package com.wlf.annotation;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/8 10:33
 */
public @interface Listener {
    String value() default "";
    String mapping() ;
}
