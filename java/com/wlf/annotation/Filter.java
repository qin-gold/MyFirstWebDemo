package com.wlf.annotation;

import java.lang.annotation.*;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/8 9:21
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Filter {
    String value() default "";
    String urlPatton() ;
}
