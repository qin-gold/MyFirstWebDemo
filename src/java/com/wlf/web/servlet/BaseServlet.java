package com.wlf.web.servlet;

import com.wlf.web.listener.ThymeleafListener;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 一个模板Servlet
 *
 * @author QinShijiao
 * @version 1.0
 * @date 2021-04-28 15:11
 */
public class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void postJson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
    }

    private void post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");
    }

    protected void returnJson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.postJson(req, resp);
    }

    protected void defaultReturn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.post(req, resp);
    }

    protected void engineStart(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
        TemplateEngine engine = ThymeleafListener.getTemplateEngine(req.getServletContext());
        engine.process(path, new WebContext(req, resp, req.getServletContext()), resp.getWriter());
    }
}
