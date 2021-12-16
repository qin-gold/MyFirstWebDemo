package com.wlf.core.web.base.filter.login;

import com.alibaba.fastjson.JSON;
import com.wlf.core.domain.dto.Result;
import com.wlf.core.domain.dto.ReturnMsg;
import com.wlf.core.enums.CodeEnum;
import com.wlf.core.enums.HttpHeaderEnum;
import com.wlf.core.enums.MsgCode;
import com.wlf.core.utlis.CacheUtils;
import com.wlf.core.utlis.EqualsUtils;
import com.wlf.core.utlis.JwtUtils;
import com.wlf.core.web.base.filter.BaseFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;


//@WebFilter("/*")
public class LoginFilter extends BaseFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String uri = httpServletRequest.getRequestURI();
        if ("/".equals(uri) || "/index.html".equals(uri) || "/login".equals(uri) || "/register".equals(uri) || "/index".equals(uri) || EqualsUtils.equalsAll(uri)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = JwtUtils.getValue(httpServletRequest);
        if (token != null && null != (CacheUtils.getId(token))) {
            filterChain.doFilter(request, response);
            return;
        }
        String jsonString = JSON.toJSONString(new Result(CodeEnum.UNAUTHORIZED.getStatusZh(), new ReturnMsg(MsgCode.ERR012).getMsg(), null));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        httpServletResponse.getWriter().write(jsonString);
        Properties config = (Properties) request.getServletContext().getAttribute("config");
        httpServletResponse.setHeader(HttpHeaderEnum.Refresh.getStatusValue(), "3" + ";url=" + config.getProperty("redirectUrl") + "");
    }

}
