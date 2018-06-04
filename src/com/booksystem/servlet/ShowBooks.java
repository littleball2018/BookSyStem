package com.booksystem.servlet;

import com.booksystem.bean.Book;
import com.booksystem.bean.BorrowBook;
import com.booksystem.util.DBUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ShowBooks extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String userRequest = req.getParameter("userrequest");
        if(StringUtils.isBlank(userRequest)){
            userRequest = (String) req.getAttribute("userrequest");
        }
        String username = (String) req.getSession().getAttribute("username");
        System.out.println("--------userRequest----" + userRequest);
        System.out.println("--------username-------" + username);

        if (userRequest != "" || !userRequest.equals("") || userRequest != null) {
            DBUtil dbUtil = new DBUtil();
            ResultSet resultSet = null;
            if (userRequest.equals("showMyBorrow")) {
                if (dbUtil.init()) {
                    System.out.println("-----------init()成功");
                    String bookid = "";
                    String borrowtime = "";
                    String returntime = "";
                    ResultSet resultSet1 = null;
                    BorrowBook borrowBook = null;
                    resultSet = dbUtil.userBorrow(username);
                    List<BorrowBook> borrowBookList = new ArrayList<BorrowBook>();

                    try {
                        while (resultSet.next()) {

                            bookid = resultSet.getString("bookid");
                            System.out.println("----------bookid:"+bookid);
                            borrowtime = resultSet.getString("borrowtime");
                            returntime = resultSet.getString("returntime");
                            resultSet1 = dbUtil.selectBookByBookid(bookid);
                            if (resultSet1.next()) {
                                System.out.println("--------resultSet1有next()");
                                borrowBook = new BorrowBook(username,
                                        bookid,
                                        resultSet1.getString("bookname"),
                                        resultSet1.getString("bookauthor"),
                                        resultSet1.getDouble("bookprice"),
                                        borrowtime,
                                        returntime);
                                System.out.println(borrowBook.getBookid() + borrowBook.getBookname() + borrowBook.getBookauthor() + borrowBook.getBookprice());
                                borrowBookList.add(borrowBook);
                            }
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    req.setAttribute("borrowBookList", borrowBookList);
                    System.out.println(borrowBookList);
                    dbUtil.close();
                    System.out.println("-------------showmyborrow");
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("showmyborrow.jsp");
                    requestDispatcher.forward(req, resp);
                    return;
                }

            } else if (userRequest.equals("showAllBooks")) {
                if (dbUtil.init()) {
                    String bookid = "";
                    String bookname = "";
                    String bookauthor = "";
                    String bookprice = "";
                    resultSet = dbUtil.selectAllBooks();
                    Book book = null;
                    List<Book> allBookList = new ArrayList<Book>();
                    try {
                        while (resultSet.next()) {
                            bookid = resultSet.getString("bookid");
                            bookname = resultSet.getString("bookname");
                            bookauthor = resultSet.getString("bookauthor");
                            bookprice = resultSet.getString("bookprice");
                            book = new Book(bookid, bookname, bookauthor, bookprice);
                            allBookList.add(book);
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    req.setAttribute("allBookList", allBookList);
                    dbUtil.close();
                    System.out.println("-------------showallbooks");
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("showallbooks.jsp");
                    requestDispatcher.forward(req, resp);
                    return;
                }
            }

        } else {
            resp.sendRedirect("showbooks");
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
