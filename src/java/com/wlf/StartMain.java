package com.wlf;

import com.wlf.core.annotation.Table;
import com.wlf.core.utlis.JDBCUtils;
import com.wlf.core.utlis.PropertiesLoadUtils;
import com.wlf.core.utlis.Scanner;
import com.wlf.core.web.base.main.QinStart;
import com.wlf.core.web.base.main.QinStartConfig;
import com.wlf.core.web.base.plugin.ThymeleafExt;
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
