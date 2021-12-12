package com.wlf.web.base.listener;


import javax.servlet.ServletRequestEvent;
import javax.servlet.annotation.WebListener;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/12 18:08
 */
@WebListener
public class LogListener extends BaseListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }
}
