package com.booksystem.servlet;

import com.booksystem.util.DBUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AgainBorrowBook extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username = (String)req.getSession().getAttribute("username");
        String bookid = req.getParameter("hiddenBookid");
        DBUtil dbUtil = new DBUtil();
        int resultSetInt = 0;
        if(dbUtil.init()){
            resultSetInt = dbUtil.againBorrow(username,bookid);
            if (resultSetInt!=0){
                req.setAttribute("userrequest","showMyBorrow");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("showbooks");
                requestDispatcher.forward(req,resp);

            }
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
