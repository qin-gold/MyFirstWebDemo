package com.wlf;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.level.Level;
import com.wlf.utlis.JDBCUtils;
import com.wlf.utlis.PropertiesLoadUtils;
import com.wlf.utlis.Scanner;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.*;

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

    static {
        jettyConfig = PropertiesLoadUtils.load("jettyConfig.properties");
        config = PropertiesLoadUtils.load("config.properties");
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        Server server = new Server(Integer.parseInt(jettyConfig.getProperty("Port")));
        WebAppContext appContext = new WebAppContext();
        appContext.setConfigurationDiscovered(true);
        appContext.setParentLoaderPriority(true);
        appContext.setThrowUnavailableOnStartupException(true);
        appContext.setContextPath(jettyConfig.getProperty("ContextPath"));
        appContext.setResourceBase(jettyConfig.getProperty("ViewPath"));
        appContext.setConfigurations(new Configuration[]{
                        new AnnotationConfiguration(),
                        new WebInfConfiguration(),
                        new WebXmlConfiguration(),
                        new MetaInfConfiguration(),
                        new FragmentConfiguration(),
                        new EnvConfiguration(),
                        new PlusConfiguration(),
                        new JettyWebXmlConfiguration()
                });
        if (Boolean.parseBoolean(config.getProperty("GeneraMode"))) {
            log.info("加载数据库开始", Level.INFO);
            JDBCUtils.initConnection();
            Scanner.init(jettyConfig.getProperty("ScannerModel"), com.wlf.annotation.Table.class);
            log.info("加载数据库结束", Level.INFO);
        }
        server.setHandler(appContext);
        System.out.println("Starting web server on port: " + jettyConfig.getProperty("Port"));
        server.start();
        System.out.println("Starting Complete. Welcome To The Bast World");
        server.join();
    }

}
