package com.wlf.core.web.base.main;

import com.wlf.core.web.base.plugin.JspExt;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.jsp.JettyJspServlet;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.*;


/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/22 12:56
 */
@Slf4j
public class Start implements StartConfig {
    private Server server;
    private WebAppContext webAppContext;
    private Integer port;
    private String context;
    private String viewPath;

    private static final Start start = new Start();

    public Start(Integer port, String context, String viewPath ) {
        this.port = port;
        this.context = context;
        this.viewPath = viewPath;
    }

    private Start() {

    }

    private void initContext() {
        server = new Server(port);
        webAppContext = new WebAppContext();
        webAppContext.setThrowUnavailableOnStartupException(true);
        webAppContext.setParentLoaderPriority(true);
        webAppContext.setConfigurationDiscovered(true);
        webAppContext.setContextPath(context);
        webAppContext.setResourceBase(viewPath);
//        webAppContext.addBean(new JspExt(webAppContext));
//        webAppContext.addServlet(JettyJspServlet.class,"*.jsp");
//        webAppContext.setAttribute(
//                "org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
//                ".*/[^/]*servlet-api-[^/]*\\.jar$|.*/javax.servlet.jsp.jstl-.*\\.jar$|.*/[^/]*taglibs.*\\.jar$" );
        webAppContext.setConfigurations(new Configuration[]{
                new AnnotationConfiguration(),
                new WebInfConfiguration(),
                new WebXmlConfiguration(),
                new MetaInfConfiguration(),
                new FragmentConfiguration(),
                new EnvConfiguration(),
                new PlusConfiguration(),
                new JettyWebXmlConfiguration()
        });
        server.setHandler(webAppContext);
    }


    @Override
    public void start() {
        initContext();
        try {
            server.start();
            log.info("Starting web server on port:{}",port);
            log.info("Starting Complete. Welcome To The Qin World");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("^v^ ^v^ ^v^ ^v^ ^v^");
    }
}
