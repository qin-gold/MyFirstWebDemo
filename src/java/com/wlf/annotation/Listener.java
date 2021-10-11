package com.wlf.annotation;

import java.lang.annotation.*;

/**
 * 模拟WebListener注解
 *
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/8 10:33
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Listener {
    String value() default "";
}
