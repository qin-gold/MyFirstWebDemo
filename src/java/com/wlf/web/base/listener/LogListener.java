package com.wlf.web.base.listener;


import com.wlf.annotation.Listener;

import javax.servlet.ServletRequestEvent;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/12 18:08
 */
@Listener
public class LogListener extends BaseListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }
}
