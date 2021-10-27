package com.wlf.web.base.main;

import com.wlf.annotation.Listener;
import com.wlf.annotation.Table;
import com.wlf.utlis.DbGenerator;
import com.wlf.utlis.JDBCUtils;
import com.wlf.utlis.Scanner;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/27 18:53
 */
@Listener
public class SystemListener implements ServletContextListener {
    private QinStart start;

    @Override
    public void contextInitialized(ServletContextEvent event) {
//        JDBCUtils.initConnection();
//        Scanner.init("com.wlf.domain",Table.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }
}
