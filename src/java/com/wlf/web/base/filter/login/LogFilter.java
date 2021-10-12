package com.wlf.web.base.filter.login;

import com.wlf.annotation.Filter;
import com.wlf.utlis.EqualsUtils;
import com.wlf.web.base.filter.BaseFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 日志过滤器
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/12 15:08
 */
@Filter(urlPatton = "/*")
public class LogFilter extends BaseFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (EqualsUtils.equalsAll(httpServletRequest.getRequestURI())){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

    }

    @Override
    public boolean before(Object target, Method method, Object[] args) {
    return false;
    }
}
