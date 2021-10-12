package com.wlf.web.base.filter.login;

import cn.hutool.jwt.JWT;
import com.alibaba.fastjson.JSON;
import com.wlf.annotation.Filter;
import com.wlf.domain.dto.Result;
import com.wlf.domain.dto.ReturnMsg;
import com.wlf.msgEnum.CodeEnum;
import com.wlf.msgEnum.HttpHeaderEnum;
import com.wlf.msgEnum.MsgCode;
import com.wlf.utlis.CacheUtils;
import com.wlf.utlis.EqualsUtils;
import com.wlf.utlis.JwtUtils;
import com.wlf.web.base.filter.BaseFilter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;


@Filter(urlPatton = "/*")
public class LoginFilter extends BaseFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String uri = httpServletRequest.getRequestURI();
        if ("/login".equals(uri)||"/register".equals(uri)||"/index".equals(uri)||EqualsUtils.equalsAll(uri)){
            filterChain.doFilter(request, response);
            return;
        }
        String token = JwtUtils.getValue(httpServletRequest);
        if (token!=null&&null!=(CacheUtils.getId(token))){
            filterChain.doFilter(request, response);
            return;
        }
        String jsonString = JSON.toJSONString(new Result(CodeEnum.UNAUTHORIZED.getStatusZh(), new ReturnMsg(MsgCode.ERR012).getMsg(), null));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        httpServletResponse.getWriter().write(jsonString);
        Properties config = (Properties)request.getServletContext().getAttribute("config");
        httpServletResponse.setHeader(HttpHeaderEnum.Refresh.getStatusValue(),"3"+";url="+config.getProperty("redirectUrl")+"");
    }

}
