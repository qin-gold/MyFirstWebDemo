package com.wlf.core.annotation;

import java.lang.annotation.*;

/**
 * 这是一个判断数据是否为空的注解
 *
 * @author QinShijiao
 * @version 1.0
 * @date 2021-04-29 9:53
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckNull {
    String value() default "";

    String Msg() default "";
}
