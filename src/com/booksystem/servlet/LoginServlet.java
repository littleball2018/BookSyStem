package com.booksystem.servlet;

import com.booksystem.util.DBUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        DBUtil dbUtil = new DBUtil();
        ResultSet resultSet = null;
        if (dbUtil.init()) {
            resultSet = dbUtil.selectByUsernamePassword(username, password);
            try {
                if (!resultSet.next()) {
                    dbUtil.close();
                    resp.sendRedirect("login.jsp");

                    return;
                } else {
                    HttpSession session=req.getSession();
                    session.setAttribute("username",username);
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/loginSuccess.jsp");
                    requestDispatcher.forward(req, resp);
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        dbUtil.close();
        resp.sendRedirect("login.jsp");
    }
}

