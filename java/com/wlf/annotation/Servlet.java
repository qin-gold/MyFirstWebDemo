package com.wlf.annotation;

import java.lang.annotation.*;

/**
 * 模拟WebServlet注解
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/8 9:21
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Servlet {
    String value() default "";
    String mapping() ;
}
