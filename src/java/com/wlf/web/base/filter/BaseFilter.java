package com.wlf.web.base.filter;

import cn.hutool.aop.aspects.SimpleAspect;

import javax.servlet.*;
import java.io.IOException;

/**
 * 基础Filter 继承了一个简易AOP
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/8 12:33
 */
public class BaseFilter extends SimpleAspect implements Filter   {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
