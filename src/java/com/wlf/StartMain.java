package com.wlf;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.wlf.annotation.Table;
import com.wlf.utlis.DbGenerator;
import com.wlf.utlis.JDBCUtils;
import com.wlf.utlis.PropertiesLoadUtils;
import com.wlf.web.base.config.Config;
import com.wlf.web.base.main.QinStart;
import com.wlf.web.base.main.QinStartConfig;

import java.util.*;

/**
 * @author Qin
 * @createDate 2021/4/28 0:10
 * @updateDate 2021/4/28 0:10
 */
public class StartMain extends QinStartConfig {
    private static final Log log = LogFactory.get();
    private static final Properties jettyConfig;

    static {
        jettyConfig = PropertiesLoadUtils.load("jettyConfig.properties");
    }

    //@SuppressWarnings("unchecked")
//    public static void main(String[] args) throws Exception {
//        Server server = new Server(Integer.parseInt(jettyConfig.getProperty("Port")));
//        WebAppContext appContext = new WebAppContext();
//        appContext.setThrowUnavailableOnStartupException(true);
//        appContext.setContextPath(jettyConfig.getProperty("ContextPath"));
//        appContext.setResourceBase(jettyConfig.getProperty("ViewPath"));
//        /***********************************/
//        log.info("加载Web开始", Level.INFO);
//        map = Scanner.init(jettyConfig.getProperty("ScannerWeb"), com.wlf.annotation.Filter.class);
//        assert map != null;
//        set = map.keySet();
//        for (Class<?> aClass : set) {
//            String mapping = map.get(aClass);
//            log.log(Level.INFO, "加载Filter------  " + aClass.toString() + " Mapping--------  " + mapping);
//            appContext.addFilter((Class<? extends Filter>) aClass, mapping, EnumSet.of(DispatcherType.REQUEST));
//        }
//        map.clear();
//        set.clear();
//        map = Scanner.init(jettyConfig.getProperty("ScannerWeb"), com.wlf.annotation.Servlet.class);
//        assert map != null;
//        set = map.keySet();
//        for (Class<?> aClass : set) {
//            String url_patton = map.get(aClass);
//            log.log(Level.INFO, "加载Servlet------  " + aClass.toString() + " Url_patton--------  " + url_patton);
//            appContext.addServlet((Class<? extends Servlet>) aClass, url_patton);
//            staticMap.put(url_patton, aClass);
//        }
//        map.clear();
//        set.clear();
//        map = Scanner.init(jettyConfig.getProperty("ScannerWeb"), com.wlf.annotation.Listener.class);
//        assert map != null;
//        set = map.keySet();
//        for (Class<?> aClass : set) {
//            log.log(Level.INFO, "加载Listener------  " + aClass.toString());
//            ServletContextListener servletContextListener = (ServletContextListener) aClass.getDeclaredConstructor().newInstance();
//            appContext.addEventListener(servletContextListener);
//        }
//
//        log.info("加载Web结束", Level.INFO);
//        if (Boolean.parseBoolean(StartMain.config.getProperty("GeneraMode"))) {
//            log.info("加载数据库开始", Level.INFO);
//            JDBCUtils.initConnection();
//            Scanner.init(jettyConfig.getProperty("ScannerModel"), com.wlf.annotation.Table.class);
//            log.info("加载数据库结束", Level.INFO);
//        }
//        /***********************************/
////        ServletMapping[] mappings = appContext.getServletHandler().getServletMappings();
////        for (ServletMapping mapping : mappings) {
////        }
//        server.setHandler(appContext);
//        System.out.println("Starting web server on port: " + jettyConfig.getProperty("Port"));
//        server.start();
//        System.out.println("Starting Complete. Welcome To The Bast World");
//        server.join();
//    }

    @Override
    public void confRoute() {

    }

    @Override
    public void afterStart() {
        System.out.println("服务器就要启动了哦");
    }

    @Override
    public void initOther() {
        //初始化数据库
        JDBCUtils.initConnection();
        DbGenerator.initDb(Table.class,jettyConfig.getProperty("ScannerModel"));
    }

    public static void main(String[] args) {
        QinStart.start(
                Integer.parseInt(jettyConfig.getProperty("Port")),
                jettyConfig.getProperty("ContextPath"),
                jettyConfig.getProperty("ViewPath"));

    }
}
