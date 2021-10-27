package com.wlf.web.base.main;

import com.wlf.web.base.config.FilterConfig;
import com.wlf.web.base.config.ListenerConfig;
import com.wlf.web.base.config.ServletConfig;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/27 14:00
 */
public class QinStart {
    private static final FilterConfig filterConfig =  new FilterConfig();
    private static final ServletConfig servletConfig = new ServletConfig();
    private static final ListenerConfig listenerConfig = new ListenerConfig();
    private static Start start;
    private static QinStartConfig config;

    public static void init(QinStartConfig config) {
     QinStart.config = config;
    }

    public static void start(Integer port, String context, String viewPath) {
        start = new Start(port, context, viewPath, servletConfig, filterConfig, listenerConfig);
        start.start();
    }

    public static void stop() {
        start.stop();
    }

    public static void afterStart(){
        config.afterStart();
    }

    public static void beforeStop(){
        config.beforeStop();
    }

    public static void init(){
        config.initOther();
    }
}
