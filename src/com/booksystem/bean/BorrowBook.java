package com.booksystem.bean;

import java.util.Date;

public class BorrowBook {
    private String username;
    private String bookid;
    private String bookname;
    private String bookauthor;
    private double bookprice;
    private String borrowtime;
    private String returntime;

    public String getReturntime() {
        return returntime;
    }

    public void setReturntime(String returntime) {
        this.returntime = returntime;
    }

    public BorrowBook(String username, String bookid, String bookname, String bookauthor, double bookprice, String borrowtime,String returntime){
        this.username = username;
        this.bookid = bookid;
        this.bookname = bookname;
        this.bookauthor = bookauthor;
        this.bookprice = bookprice;
        this.borrowtime = borrowtime;
        this.returntime = returntime;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookauthor() {
        return bookauthor;
    }

    public void setBookauthor(String bookauthor) {
        this.bookauthor = bookauthor;
    }

    public double getBookprice() {
        return bookprice;
    }

    public void setBookprice(double bookprice) {
        this.bookprice = bookprice;
    }

    public String getBorrowtime() {
        return borrowtime;
    }

    public void setBorrowtime(String borrowtime) {
        this.borrowtime = borrowtime;
    }
}
