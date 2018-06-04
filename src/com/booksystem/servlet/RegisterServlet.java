package com.booksystem.servlet;

import com.booksystem.util.DBUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        System.out.println(username);
        String telephone = req.getParameter("telephone");
        String password = req.getParameter("password");
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(telephone) && StringUtils.isNotBlank(password)) {
            System.out.println("---=-------if");
            System.out.println(username+"-"+telephone+"-"+password);
            DBUtil dbUtil = new DBUtil();
            if (dbUtil.init()) {
                int result = dbUtil.insertUserInfo(username, telephone, password);
                if (result != 0) {
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/registerSuccess.jsp");
                    requestDispatcher.forward(req, resp);
                    return;

                }

            }
            dbUtil.close();
            resp.sendRedirect("register.jsp");
        } else {
            System.out.println("-----------else");
            resp.sendRedirect("register.jsp");
        }


    }

}
