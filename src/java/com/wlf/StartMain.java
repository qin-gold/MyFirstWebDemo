package com.wlf;

import cn.hutool.db.Db;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.level.Level;
import com.wlf.utlis.JDBCUtils;
import com.wlf.utlis.PropertiesLoadUtils;
import com.wlf.utlis.Scanner;
import com.wlf.web.base.main.QinStart;
import com.wlf.web.base.main.QinStartConfig;
import com.wlf.web.base.main.Start;
import com.wlf.web.base.plugin.ThymeleafExt;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.*;

import javax.servlet.ServletContext;
import java.util.*;

/**
 * @author Qin
 * @createDate 2021/4/28 0:10
 * @updateDate 2021/4/28 0:10
 */
@Slf4j
public class StartMain extends QinStartConfig {
    private static final Properties jettyConfig;
    private static final Properties config;

    static {
        jettyConfig = PropertiesLoadUtils.load("jettyConfig.properties");
        config = PropertiesLoadUtils.load("config.properties");
    }

    public static void main(String[] args) throws Exception {
        QinStart.start(Integer.parseInt(jettyConfig.getProperty("Port")),jettyConfig.getProperty("ContextPath"),jettyConfig.getProperty("WebPath"));
    }

    @Override
    public void afterStart() {
       JDBCUtils.initConnection();
    }

    @Override
    public void beforeStop() {

    }

    @Override
    public void initOther(ServletContext servletContext) {

        log.info("servlet{}",servletContext);
        ThymeleafExt.init(servletContext);
    }
}
