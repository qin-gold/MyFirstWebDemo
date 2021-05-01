package com.wlf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 这是一个判断是否为主键的注解
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/4/30 4:09
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TablePk {
    boolean isPk() default true;
    boolean increment() default false;
}
