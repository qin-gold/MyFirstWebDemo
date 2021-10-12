package com.wlf.web.base.filter.login;

import com.wlf.annotation.Filter;
import com.wlf.utlis.XssHttpServletRequestWrapper;
import com.wlf.web.base.filter.BaseFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/11 21:39
 */
@Filter(urlPatton = "/*")
public class XssFilter extends BaseFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest)servletRequest);
        filterChain.doFilter(xssRequest, servletResponse);
    }
}
