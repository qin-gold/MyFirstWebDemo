package com.wlf.core.web.base.servlet;

import cn.hutool.aop.aspects.Aspect;
import com.wlf.core.web.base.core.Model;
import com.wlf.core.web.base.plugin.ThymeleafExt;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 一个模板Servlet
 *
 * @author QinShijiao
 * @version 1.0
 * @date 2021-04-28 15:11
 */
@Slf4j
public class BaseServlet extends HttpServlet implements Aspect {

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

    public void returnJson(HttpServletRequest req, HttpServletResponse resp, String json) throws ServletException, IOException {
        this.postJson(req, resp);
        resp.getWriter().write(json);
    }

    public void defaultReturn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.post(req, resp);
    }

    public void thyRender(HttpServletRequest req, HttpServletResponse resp, String path) {
        TemplateEngine engine = ThymeleafExt.getTemplateEngine(req.getServletContext());
        if (engine != null) {
            try {
                engine.process(path, new WebContext(req, resp, req.getServletContext()), resp.getWriter());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        throw new RuntimeException("Thymeleaf template engine is not configured");
    }

    public void thyRender(Model model, String path) {
        this.thyRender(model.getRequest(), model.getResponse(), path);
    }

    @Override
    public boolean before(Object target, Method method, Object[] args) {
        return true;
    }

    @Override
    public boolean after(Object target, Method method, Object[] args, Object returnVal) {
        return true;
    }

    @Override
    public boolean afterException(Object target, Method method, Object[] args, Throwable e) {
        return true;
    }
}
