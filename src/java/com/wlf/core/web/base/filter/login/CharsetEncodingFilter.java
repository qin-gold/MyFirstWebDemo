package com.wlf.core.web.base.filter.login;

import com.wlf.core.web.base.filter.BaseFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/11 21:42
 */
@WebFilter("/*")
public class CharsetEncodingFilter extends BaseFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       if (super.withOutFilter(servletRequest)){
           servletRequest.setCharacterEncoding("utf-8");
           servletResponse.setCharacterEncoding("utf-8");
       }
       filterChain.doFilter(servletRequest,servletResponse);
    }
}
