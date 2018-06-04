package com.booksystem.util;

import java.sql.*;


public class DBUtil {
    String dbDriver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/booksystem?useUnicode=true&characterEncoding=utf-8";
    String user = "root";
    String pwd = "123456";
    Connection connection = null;
    PreparedStatement statement = null;

//    public static void main(String[] args) {
//        System.out.println(new java.util.Date().getTime());
//    }

    public boolean init() {
        boolean flag = false;
        try {
            Class.forName(dbDriver);
            connection = DriverManager.getConnection(url, user, pwd);
            flag = true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    //--------------注册用户信息------------------------
    public int insertUserInfo(String username, String telephone, String password) {
        String sql = "INSERT `userinfo`(`username`,`telephone`,`password`) VALUE (?,?,?)";
        int result = 0;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, telephone);
            statement.setString(3, password);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    //--------------添加图书------------------------
    public int insertBooks(String bookid, String bookname, String bookauthor, double bookprice) {
        String sql = "INSERT `books`(`bookid`,`bookname`,`bookauthor`,`bookprice`) VALUE (?,?,?,?)";
        int result = 0;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, bookid);
            statement.setString(2, bookname);
            statement.setString(3, bookauthor);
            statement.setDouble(4, bookprice);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    //--------------借书------------------------
    public int insertBorrow(String username, String bookid) {
        String sql = "INSERT `borrow`(`username`,`bookid`,`borrowtime`,`returntime`) VALUE (?,?,?,?)";
        int result = 0;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, bookid);
            java.util.Date date = new java.util.Date();
            statement.setString(3, TimeUtil.dateToString(date));
            statement.setString(4, TimeUtil.dateToString(TimeUtil.After30Date(date)));

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public int againBorrow(String username, String bookid) {
        String insertSql = "UPDATE `borrow` SET `borrowtime` = ? , `returntime` = ? WHERE `username` = ? AND `bookid` = ? ";
        int resultSetInt = 0;
        String newBorrowtime = TimeUtil.dateToString(new java.util.Date());
        String newReturntime = TimeUtil.dateToString(TimeUtil.After30Date(TimeUtil.stringToDate(newBorrowtime)));
        try {
            statement = connection.prepareStatement(insertSql);
            statement.setString(1, newBorrowtime);
            statement.setString(2, newReturntime);
            statement.setString(3, username);
            statement.setString(4, bookid);
            resultSetInt = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return resultSetInt;
    }

    //--------------匹配用户密码------------------------
    public ResultSet selectByUsernamePassword(String username, String password) {
        String sql = "SELECT * FROM `userinfo` WHERE `username` = ? AND `password` = ?";
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;

    }

    //--------------查找展示所有图书------------------------
    public ResultSet selectAllBooks() {
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM `books`");
            resultSet = statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;

    }

    //--------------根据书号查找图书------------------------
    public ResultSet selectBookByBookid(String bookid) {
        String sql = "SELECT * FROM `books` WHERE `bookid` = ?";
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, bookid);
            resultSet = statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    //--------------根据书名查找图书------------------------
    public ResultSet selectBookByBookname(String bookname) {
        String sql = "SELECT * FROM `books` WHERE `bookname` = ?";
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, bookname);
            resultSet = statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    //--------------根据作者查找图书------------------------
    public ResultSet selectBookByBookauthor(String bookauthor) {
        String sql = "SELECT * FROM `books` WHERE `bookauthor` = ?";
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, bookauthor);
            resultSet = statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    //--------------查询该书是否已被该用户借阅------------------------
    public ResultSet isBorrowed(String username, String bookid) {
        String sql = "SELECT * FROM `borrow` WHERE `username` = ? AND `bookid` = ?";
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, bookid);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;

    }

    //--------------查询用户借阅的图书号------------------------
    public ResultSet userBorrow(String username) {
        String sql = "SELECT * FROM `borrow` WHERE `username` = ?";
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
