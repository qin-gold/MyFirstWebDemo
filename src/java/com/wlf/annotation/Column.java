package com.wlf.annotation;

import com.wlf.msgEnum.DbType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 这是设置字段的注解
 *
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/4/30 4:09
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String value();

    DbType type();

    int length() default 32;

    boolean notNull() default false;

    String remark() default "";
}
