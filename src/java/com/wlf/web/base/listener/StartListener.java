package com.wlf.web.base.listener;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.level.Level;
import com.mysql.cj.util.LogUtils;
import com.wlf.StartMain;
import com.wlf.annotation.Listener;
import com.wlf.utlis.JDBCUtils;
import com.wlf.utlis.PropertiesLoadUtils;

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
        ServletContext context = servletContextEvent.getServletContext();
        Properties load = PropertiesLoadUtils.load("config.properties");
        log.info("加载配置文件", Level.INFO);
        context.setAttribute("config",load);
        log.info("加载数据库", Level.INFO);
        JDBCUtils.initConnection();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("------系统销毁------");
    }
}
