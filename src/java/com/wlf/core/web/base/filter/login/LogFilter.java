package com.wlf.core.web.base.filter.login;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.wlf.core.service.base.LogService;
import com.wlf.core.service.base.impl.LogServiceImpl;
import com.wlf.core.web.base.filter.BaseFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 日志过滤器
 *
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/12 15:08
 */
//@Filter(urlPatton = "/*")
@WebFilter("/*")
public class LogFilter extends BaseFilter {

    private static final LogService logService = new LogServiceImpl();
    private static final Log log = LogFactory.get();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        String uri = request.getRequestURI();
//        if (super.withOutFilter(request)) {
//            Class<?> aClass = ServletConfig.getServlet(uri);
//            if (aClass != null) {
//                String id = JwtUtils.getValue(request);
//                com.wlf.annotation.Log log = aClass.getAnnotation(com.wlf.annotation.Log.class);
//                if (log != null) {
//                    String remark = log.remark();
//                    String ip = request.getRemoteAddr();
//                    if (id != null) {
//                        User user = CacheUtils.getUser(id);
//                        logService.save(new com.wlf.domain.base.Log(UUID.fastUUID().toString(), remark, user.getId(), user.getName(), ip, uri, null, log.remark()));
//                        LogFilter.log.info("[+" + log.title() + "+]", Level.INFO);
//                    }
//                }
//            }
//        }
    }

}