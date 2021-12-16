package com.wlf.core.web.base.annotation;


import java.lang.annotation.*;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/12/16 11:29
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping{
    String value() default "";
}
