package com.wlf.web.base.main;

import com.wlf.web.base.config.FilterConfig;
import com.wlf.web.base.config.ListenerConfig;
import com.wlf.web.base.config.ServletConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;


/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/22 12:56
 */
public class Start implements StartConfig {
    private Server server;
    private WebAppContext webAppContext;
    private Integer port;
    private String context;
    private String viewPath;
    private ServletConfig servletConfig;
    private FilterConfig filterConfig;
    private ListenerConfig listenerConfig;

    private static final Start start = new Start();

    public Start(Integer port, String context, String viewPath, ServletConfig servletConfig, FilterConfig filterConfig, ListenerConfig listenerConfig) {
        this.port = port;
        this.context = context;
        this.viewPath = viewPath;
        this.servletConfig = servletConfig;
        this.filterConfig = filterConfig;
        this.listenerConfig = listenerConfig;
    }

    private Start() {

    }

    private void initContext() {
        server = new Server(port);
        webAppContext = new WebAppContext();
        webAppContext.setThrowUnavailableOnStartupException(true);
        webAppContext.setContextPath(context);
        webAppContext.setResourceBase(viewPath);
        server.setHandler(webAppContext);
    }


    public void initWeb() {
        servletConfig.config(webAppContext);
        filterConfig.config(webAppContext);
        listenerConfig.config(webAppContext);
    }

    @Override
    public void start() {
        initContext();
        initWeb();
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Starting web server on port: " + port);
        System.out.println("Starting Complete. Welcome To The Qin World");
    }

    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("^v^ ^v^ ^v^ ^v^ ^v^");
    }
}
