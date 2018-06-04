package com.booksystem.bean;

public class Book {
    private String bookid;
    private String bookname;
    private String bookauthor;
    private String bookprice;

    public Book(String bookid,String bookname,String bookauthor,String bookprice){
        this.bookid = bookid;
        this.bookname = bookname;
        this.bookauthor = bookauthor;
        this.bookprice = bookprice;

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

    public String getBookprice() {
        return bookprice;
    }

    public void setBookprice(String bookprice) {
        this.bookprice = bookprice;
    }
}
