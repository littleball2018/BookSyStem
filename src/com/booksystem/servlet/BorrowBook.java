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

public class BorrowBook extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username = (String) req.getSession().getAttribute("username");
        System.out.println("--------getAttribute-username : "+username);
        String bookid = req.getParameter("hiddenBookid");
        DBUtil dbUtil = new DBUtil();
        ResultSet resultSet = null;
        if(dbUtil.init()){
            resultSet = dbUtil.isBorrowed(username,bookid);



            try {
                if(resultSet.next()){
                    System.out.println("-----------c1:"+resultSet.getString(1));
                    System.out.println("---------借过了");
                    req.setAttribute("userrequest","showMyBorrow");
                    req.setAttribute("borrowedOrNot","y");
//                        resp.sendRedirect("showbooks");
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("showbooks");
                    requestDispatcher.forward(req,resp);

                }else{
                    System.out.println("----------没有借，可以借");
                    int resultSetInt = dbUtil.insertBorrow(username,bookid);
                    System.out.println("-----------bookid:"+bookid);
                    if(resultSetInt!=0){
                        req.setAttribute("userrequest","showMyBorrow");
                        req.setAttribute("borrowedOrNot","n");
//                        resp.sendRedirect("showbooks");
                        RequestDispatcher requestDispatcher = req.getRequestDispatcher("showbooks");
                        requestDispatcher.forward(req,resp);

                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
