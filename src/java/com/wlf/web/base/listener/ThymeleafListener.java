package com.wlf.web.base.listener;

import com.wlf.annotation.Listener;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * 初始化thymeleaf
 *
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/9 0:03
 */
@Listener
public class ThymeleafListener extends BaseListener {
    private static final String TEMPLATE_ENGINE_INST = "TemplateEngine";

    public static void storeTemplateEngine(ServletContext context, TemplateEngine engine) {
        context.setAttribute(TEMPLATE_ENGINE_INST, engine);
    }

    public static TemplateEngine getTemplateEngine(ServletContext context) {
        return (TemplateEngine) context.getAttribute(TEMPLATE_ENGINE_INST);
    }

    @Override
    public void contextInitialized(ServletContextEvent ser) {
        ServletContextTemplateResolver tmp = new ServletContextTemplateResolver(ser.getServletContext());
        tmp.setTemplateMode(TemplateMode.HTML);
        tmp.setPrefix("/WEB-INF/pages/");
        tmp.setSuffix(".html");
        tmp.setCacheTTLMs(3600000L);
        tmp.setCacheable(false);
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(tmp);
        storeTemplateEngine(ser.getServletContext(), engine);
    }

    @Override
    public void contextDestroyed(ServletContextEvent ser) {

    }
}
