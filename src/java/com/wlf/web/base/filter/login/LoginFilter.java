package com.wlf.web.base.filter.login;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.wlf.annotation.Filter;
import com.wlf.domain.dto.Result;
import com.wlf.domain.dto.ReturnMsg;
import com.wlf.msgEnum.CodeEnum;
import com.wlf.msgEnum.MsgCode;
import com.wlf.utlis.CacheUtils;
import com.wlf.utlis.Jwtutils;
import com.wlf.web.base.filter.BaseFilter;
import com.wlf.web.base.servlet.BaseServlet;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Filter(urlPatton = "/login")
public class LoginFilter extends BaseFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")){
                String value = Jwtutils.getValue(cookie.getValue());
                if (value!=null&&null!=(CacheUtils.getId(value))){
                     filterChain.doFilter(request, response);
                }
            }
        }
        String jsonString = JSON.toJSONString(new Result(CodeEnum.UNAUTHORIZED.getStatusZh(), new ReturnMsg(MsgCode.ERR012).getMsg(), null));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        httpServletResponse.getWriter().write(jsonString);
    }

}
