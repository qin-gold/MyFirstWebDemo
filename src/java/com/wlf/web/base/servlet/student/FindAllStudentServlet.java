package com.wlf.web.base.servlet.student;

import com.wlf.annotation.Servlet;
import com.wlf.domain.dto.QueryConditions;
import com.wlf.domain.dto.Result;
import com.wlf.server.StudentServer;
import com.wlf.server.impl.StudentServerImpl;
import com.wlf.utlis.Inject;
import com.wlf.web.base.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/9 2:10
 */
@Servlet(mapping = "/findAll")
public class FindAllStudentServlet extends BaseServlet {

    private final StudentServer server = new StudentServerImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.defaultReturn(req, resp);
        QueryConditions bean = Inject.getBean(req, QueryConditions.class);
        Result all = server.findAll(bean);
        req.setAttribute("student", all.getData());
        super.thyRender(req, resp, "user/login");
    }
}
