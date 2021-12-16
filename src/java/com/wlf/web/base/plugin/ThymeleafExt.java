package com.wlf.web.base.plugin;

import com.wlf.utlis.PropertiesLoadUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import java.util.Properties;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/12/15 1:38
 */
public class ThymeleafExt {

    private static final String TEMPLATE_ENGINE_INST = "TemplateEngine";
    private static final Properties pro;

    static {
        pro = PropertiesLoadUtils.load("jettyConfig.properties");
    }

    public static TemplateEngine getTemplateEngine(ServletContext context) {
        return (TemplateEngine) context.getAttribute(TEMPLATE_ENGINE_INST);
    }

    public static void init(ServletContext servletContext) {
        ServletContextTemplateResolver tmp = new ServletContextTemplateResolver(servletContext);
        tmp.setTemplateMode(TemplateMode.HTML);
        tmp.setPrefix(pro.getProperty("ViewPath"));
        tmp.setSuffix(pro.getProperty("ViewSuffix"));
        tmp.setCacheTTLMs(Long.parseLong(pro.getProperty("CacheTTLMs")));
        tmp.setCacheable(Boolean.parseBoolean(pro.getProperty("Cacheable")));
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(tmp);
        servletContext.setAttribute(TEMPLATE_ENGINE_INST,engine);
    }

}
