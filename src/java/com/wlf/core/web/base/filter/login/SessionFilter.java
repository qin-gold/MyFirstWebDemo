package com.wlf.core.web.base.filter.login;

import com.wlf.core.utlis.EqualsUtils;
import com.wlf.core.web.base.filter.BaseFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/11 21:39
 */
@WebFilter("/*")
public class SessionFilter extends BaseFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String uri = httpServletRequest.getRequestURI();
        if ("/".equals(uri) || "/index.html".equals(uri) || "/login".equals(uri) || "/register".equals(uri) || "/index".equals(uri) || EqualsUtils.equalsAll(uri)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        System.out.println("经过权限过滤器");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
