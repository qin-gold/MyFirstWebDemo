package com.wlf;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Qin
 * @createDate 2021/4/28 0:10
 * @updateDate 2021/4/28 0:10
 */
public class StartMain {
    private static String PATH=null;
    private static String PORT="80";
    private static String VIEWPATH=null;
    static{
        Properties pro = new Properties();
        try {
            pro.load(StartMain.class.getClassLoader().getResourceAsStream("jettyConfig.properties"));
            PATH = pro.getProperty("path");
            PORT = pro.getProperty("port");
            VIEWPATH = pro.getProperty("viewPath");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) throws Exception {
        Server server =new Server(Integer.valueOf(PORT));
        WebAppContext appContext =new WebAppContext();
        appContext.setThrowUnavailableOnStartupException(true);
        appContext.setResourceBase(PATH);
        appContext.setContextPath(VIEWPATH);
        server.setHandler(appContext);
        server.start();
        server.join();
    }
}
