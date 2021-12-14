package com.wlf.web.base.main;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/27 14:00
 */
public class QinStart {
    private static Start start;
    private static QinStartConfig config;

    public void init(QinStartConfig config) {
     QinStart.config = config;
    }

    public static void start(Integer port, String context, String viewPath) {
        start = new Start(port, context, viewPath);
        start.start();
    }

    public static void stop() {
        start.stop();
    }

    public void afterStart(){
        config.afterStart();
    }

    public void beforeStop(){
        config.beforeStop();
    }

}
