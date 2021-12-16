package com.wlf;

import com.wlf.annotation.Table;
import com.wlf.utlis.JDBCUtils;
import com.wlf.utlis.PropertiesLoadUtils;
import com.wlf.utlis.Scanner;
import com.wlf.web.base.main.QinStart;
import com.wlf.web.base.main.QinStartConfig;
import com.wlf.web.base.plugin.ThymeleafExt;
import lombok.extern.slf4j.Slf4j;

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
       Scanner.init(jettyConfig.getProperty("ScannerModel"),Table.class);
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
