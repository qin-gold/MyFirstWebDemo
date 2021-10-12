package com.wlf.web.base.filter.login;

import com.wlf.annotation.Filter;
import com.wlf.web.base.filter.BaseFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/11 21:42
 */
@Filter(urlPatton = "/*")
public class CharsetEncodingFilter extends BaseFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       servletRequest.setCharacterEncoding("utf-8");
       servletResponse.setCharacterEncoding("utf-8");
        System.out.println("经过字符过滤器");
       filterChain.doFilter(servletRequest,servletResponse);
    }
}
