package com.wlf.web.servlet.student;

import com.wlf.annotation.Servlet;
import com.wlf.dao.StudentDao;
import com.wlf.dao.impl.StudentDaoImpl;
import com.wlf.server.StudentServer;
import com.wlf.server.impl.StudentServerImpl;
import com.wlf.web.servlet.BaseServlet;

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
//        server.findAll()

        req.getRequestDispatcher("user/login.html").forward(req,resp);
    }
}
