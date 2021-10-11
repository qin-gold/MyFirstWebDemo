package com.wlf.web.filter.login;

import com.wlf.annotation.Filter;
import com.wlf.web.filter.BaseFilter;

import javax.servlet.*;
import java.io.IOException;

@Filter(urlPatton = "/*")
public class LoginFilter extends BaseFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        System.out.println("每一个请求都过");
        filterChain.doFilter(request, response);
    }

}
