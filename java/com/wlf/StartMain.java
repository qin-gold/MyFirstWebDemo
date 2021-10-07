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
    private static String ContextPath ="/";
    private static String Port ="80";
    private static String ViewPath ="/";
    static{
        Properties pro = new Properties();
        try {
            pro.load(StartMain.class.getClassLoader().getResourceAsStream("jettyConfig.properties"));
            ContextPath = pro.getProperty("ContextPath");
            Port = pro.getProperty("Port");
            ViewPath = pro.getProperty("ViewPath");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) throws Exception {
        Server server =new Server(Integer.parseInt(Port));
//        ServerConnector connector = new ServerConnector(server);
//        connector.setPort(Integer.parseInt(PORT));
//        server.setConnectors(new Connector[] {connector});
//        ServletContextHandler contextHandler = new ServletContextHandler();
//        contextHandler.setContextPath(VIEWPATH);
//        contextHandler.setResourceBase(PATH);
//        //创建一个handler
//        ServletHandler handler = new ServletHandler();
//        server.setHandler(handler);


        WebAppContext appContext =new WebAppContext();
        appContext.setThrowUnavailableOnStartupException(true);
        appContext.setContextPath(ContextPath);
        appContext.setResourceBase(ViewPath);
        server.setHandler(appContext);

        System.out.println("Starting web server on port: " + Port);
        server.start();
        System.out.println("Starting Complete. Welcome To The Bast World");
        server.join();
    }
}
