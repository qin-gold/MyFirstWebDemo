package com.wlf.web.base.main;


import javax.servlet.ServletContext;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/27 14:05
 */
public abstract class QinStartConfig {

    public void afterStart(){};

    public void beforeStop(){};

    public void initOther(ServletContext servletContext){};

}
