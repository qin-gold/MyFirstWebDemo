package com.wlf;

import com.wlf.utlis.Scanner;
import com.wlf.web.listener.BaseListener;
import com.wlf.web.listener.ThymeleafListener;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.*;

/**
 * @author Qin
 * @createDate 2021/4/28 0:10
 * @updateDate 2021/4/28 0:10
 */
public class StartMain {
    private static String ContextPath ="/";
    private static String Port ="80";
    private static String ViewPath ="/";
    private static String ScannerPackage ="/";
    private static Map<Class<?>, String> map = new HashMap<>();
    private static Set<Class<?>> set = new HashSet<>();
    static{
        Properties pro = new Properties();
        try {
            pro.load(StartMain.class.getClassLoader().getResourceAsStream("jettyConfig.properties"));
            ContextPath = pro.getProperty("ContextPath");
            Port = pro.getProperty("Port");
            ViewPath = pro.getProperty("ViewPath");
            ScannerPackage =pro.getProperty("ScannerPackage");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        Server server = new Server(Integer.parseInt(Port));
        WebAppContext appContext =new WebAppContext();
        appContext.setThrowUnavailableOnStartupException(true);
        appContext.setContextPath(ContextPath);
        appContext.setResourceBase(ViewPath);
        /***********************************/
        System.out.println("加载资源开始");
        map = Scanner.init(ScannerPackage, com.wlf.annotation.Filter.class);
        set = map.keySet();
        for (Class<?> aClass : set) {
            String mapping = map.get(aClass);
            System.out.println("加载Filter------  "+aClass.toString()+" Mapping--------  "+mapping);
            appContext.addFilter((Class<? extends Filter>) aClass,mapping,EnumSet.of(DispatcherType.FORWARD));
        }
        map.clear();
        set.clear();
        map = Scanner.init(ScannerPackage, com.wlf.annotation.Servlet.class);
        set = map.keySet();
        for (Class<?> aClass : set) {
            String url_patton = map.get(aClass);
            System.out.println("加载Servlet------  "+aClass.toString()+" Url_patton--------  "+url_patton);
            appContext.addServlet((Class<? extends Servlet>) aClass,url_patton);
        }
        map.clear();
        set.clear();
        map = Scanner.init(ScannerPackage, com.wlf.annotation.Listener.class);
        set = map.keySet();
        for (Class<?> aClass : set) {
            System.out.println("加载Listener------  "+aClass.toString());
            ServletContextListener servletContextListener = (ServletContextListener)aClass.getDeclaredConstructor().newInstance();
            appContext.addEventListener(servletContextListener);
        }
        appContext.addEventListener(new ThymeleafListener());
        System.out.println("加载资源结束");
        /***********************************/
        server.setHandler(appContext);

        System.out.println("Starting web server on port: " + Port);
        server.start();
        System.out.println("Starting Complete. Welcome To The Bast World");
        server.join();
    }
}
