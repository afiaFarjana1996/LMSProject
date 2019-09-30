package com.ss.pojo;
import java.io.Serializable;

public class Book implements Serializable{
	private static final long serialVersionUID = 6440606157576383445L;
	private int bookId;
    private String bookName;
    private int bookAuthorId;
    private int bookPublisherId;
    public Book() {
    }
    public Book(int bookId,String bookName,int bookAuthorId,int bookPublisherId) {
    	this.bookId = bookId;
    	this.bookName = bookName;
    	this.bookAuthorId = bookAuthorId;
    	this.bookPublisherId = bookPublisherId;
    }
    
    public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getBookAuthorId() {
		return bookAuthorId;
	}
	public void setBookAuthorId(int bookAuthorId) {
		this.bookAuthorId = bookAuthorId;
	}
	public int getBookPublisherId() {
		return bookPublisherId;
	}
	public void setBookPublisherId(int bookPublisherId) {
		this.bookPublisherId = bookPublisherId;
	}
	
}
