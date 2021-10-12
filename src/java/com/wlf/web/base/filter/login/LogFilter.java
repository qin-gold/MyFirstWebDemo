package com.wlf.web.base.filter.login;

import cn.hutool.core.lang.UUID;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.level.Level;
import com.wlf.StartMain;
import com.wlf.annotation.Filter;
import com.wlf.domain.base.User;
import com.wlf.server.base.LogService;
import com.wlf.server.base.impl.LogServiceImpl;
import com.wlf.utlis.CacheUtils;
import com.wlf.utlis.EqualsUtils;
import com.wlf.utlis.JwtUtils;
import com.wlf.web.base.filter.BaseFilter;
import com.wlf.web.base.listener.LogListener;

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

    private static final LogService logService = new LogServiceImpl();
    private static final Log log = LogFactory.get();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        if (super.withOutFilter(request)){
            Class<?> aClass = StartMain.getServlet(uri);
            if (aClass != null) {
                String id = JwtUtils.getValue(request);
                com.wlf.annotation.Log log = aClass.getAnnotation(com.wlf.annotation.Log.class);
                if (log!=null){
                    String remark = log.remark();
                    String ip = request.getRemoteAddr();
                    if (id != null) {
                        User user = CacheUtils.getUser(id);
                        logService.save(new com.wlf.domain.base.Log(UUID.fastUUID().toString(),remark,user.getId(),user.getName(),ip,uri,null, log.remark()));
                        LogFilter.log.info("[+"+log.title()+"+]", Level.INFO);
                    }
                }
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

}
