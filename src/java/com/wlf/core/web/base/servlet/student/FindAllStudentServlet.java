package com.wlf.core.web.base.servlet.student;

import com.wlf.core.domain.dto.QueryConditions;
import com.wlf.core.domain.dto.Result;
import com.wlf.ext.service.StudentService;
import com.wlf.ext.service.impl.StudentServiceImpl;
import com.wlf.core.utlis.Inject;
import com.wlf.core.web.base.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/9 2:10
 */
public class FindAllStudentServlet extends BaseServlet {

    private final StudentService server = new StudentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.defaultReturn(req, resp);
        QueryConditions bean = Inject.getBean(req, QueryConditions.class);
        Result all = server.findAll(bean);
        req.setAttribute("student", all.getData());
        super.thyRender(req, resp, "user/login");
    }
}
