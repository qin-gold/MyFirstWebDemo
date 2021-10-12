package com.wlf;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.level.Level;
import com.wlf.utlis.PropertiesLoadUtils;
import com.wlf.utlis.Scanner;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.ServletMapping;
import org.eclipse.jetty.util.Attributes;
import org.eclipse.jetty.webapp.WebAppContext;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.servlet.ServletContextListener;
import java.util.*;

/**
 * @author Qin
 * @createDate 2021/4/28 0:10
 * @updateDate 2021/4/28 0:10
 */
public class StartMain {
    private static final Log log = LogFactory.get();
    private static final Properties jettyConfig;
    private static final Properties config;
    private static Map<Class<?>, String> map = new HashMap<>();
    private static Set<Class<?>> set = new HashSet<>();

    static {
        jettyConfig = PropertiesLoadUtils.load("jettyConfig.properties");
        config = PropertiesLoadUtils.load("config.properties");
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        Server server = new Server(Integer.parseInt(jettyConfig.getProperty("Port")));
        WebAppContext appContext = new WebAppContext();
        appContext.setThrowUnavailableOnStartupException(true);
        appContext.setContextPath(jettyConfig.getProperty("ContextPath"));
        appContext.setResourceBase(jettyConfig.getProperty("ViewPath"));
        /***********************************/
        log.info("加载Web开始", Level.INFO);
        map = Scanner.init(jettyConfig.getProperty("ScannerWeb"), com.wlf.annotation.Filter.class);
        assert map != null;
        set = map.keySet();
        for (Class<?> aClass : set) {
            String mapping = map.get(aClass);
            log.log(Level.INFO, "加载Filter------  " + aClass.toString() + " Mapping--------  " + mapping);
            appContext.addFilter((Class<? extends Filter>) aClass, mapping, EnumSet.of(DispatcherType.REQUEST));
        }
        map.clear();
        set.clear();
        map = Scanner.init(jettyConfig.getProperty("ScannerWeb"), com.wlf.annotation.Servlet.class);
        assert map != null;
        set = map.keySet();
        for (Class<?> aClass : set) {
            String url_patton = map.get(aClass);
            log.log(Level.INFO, "加载Servlet------  " + aClass.toString() + " Url_patton--------  " + url_patton);
            appContext.addServlet((Class<? extends Servlet>) aClass, url_patton);
        }
        map.clear();
        set.clear();
        map = Scanner.init(jettyConfig.getProperty("ScannerWeb"), com.wlf.annotation.Listener.class);
        assert map != null;
        set = map.keySet();
        for (Class<?> aClass : set) {
            log.log(Level.INFO, "加载Listener------  " + aClass.toString());
            ServletContextListener servletContextListener = (ServletContextListener) aClass.getDeclaredConstructor().newInstance();
            appContext.addEventListener(servletContextListener);
        }
        log.info("加载Web结束", Level.INFO);
        if (Boolean.parseBoolean(config.getProperty("GeneraMode"))){
        log.info("加载数据库开始", Level.INFO);
        Scanner.init(jettyConfig.getProperty("ScannerModel"), com.wlf.annotation.Table.class);
        log.info("加载数据库结束", Level.INFO);
        }
        /***********************************/
        ServletMapping[] mappings = appContext.getServletHandler().getServletMappings();
        for (ServletMapping mapping : mappings) {
            System.out.println(mapping.getServletName());
            System.out.println(mapping.getPathSpecs().length);
        }
        server.setHandler(appContext);
        System.out.println("Starting web server on port: " + jettyConfig.getProperty("Port"));
        server.start();
        System.out.println("Starting Complete. Welcome To The Bast World");
        server.join();
    }
}
