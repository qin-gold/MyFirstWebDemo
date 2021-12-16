package com.wlf.web.base.core;

import com.wlf.web.base.servlet.BaseServlet;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/21 13:13
 */
public class Action {
    private String actionName;
    private String actionKey;
    private Class<? extends BaseServlet> controller;

}
