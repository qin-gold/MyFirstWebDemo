package com.wlf.web.base.listener;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.level.Level;
import com.wlf.annotation.Listener;
import com.wlf.annotation.Table;
import com.wlf.utlis.JDBCUtils;
import com.wlf.utlis.PropertiesLoadUtils;
import com.wlf.utlis.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.util.Properties;

/**
 * @author Qin
 * @createTime 2021/4/28 1:28
 */
@Listener
public class StartListener extends BaseListener {

    private static final Log log = LogFactory.get();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        JDBCUtils.initConnection();
        Scanner.init("com.wlf.domain",Table.class);
        ServletContext context = servletContextEvent.getServletContext();
        Properties load = PropertiesLoadUtils.load("config.properties");
        log.info("加载配置文件", Level.INFO);
        context.setAttribute("config",load);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("------系统销毁------");
    }
}
