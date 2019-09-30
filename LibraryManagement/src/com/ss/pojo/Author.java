package com.ss.pojo;

import java.io.Serializable;

public class Author implements Serializable{
	
    private static final long serialVersionUID = 1369322705727334695L;
    private int authorId;
    private String authorName;
    private String authorPhoneNumber;
    
    public Author(){
    	
    }
    public Author(int authorId,String authorName,String authorPhoneNumber) {
    	this.authorId = authorId;
    	this.authorName = authorName;
    	this.authorPhoneNumber = authorPhoneNumber;
    }
    
     public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorPhoneNumber() {
		return authorPhoneNumber;
	}
	public void setAuthorPhoneNumber(String authorPhoneNumber) {
		this.authorPhoneNumber = authorPhoneNumber;
	}
     
}
