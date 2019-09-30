package com.ss.pojo;

import java.io.Serializable;

public class Publisher implements Serializable{
	private static final long serialVersionUID = -1236663043465649997L;
    private int publisherId;
    private String publisherName;
    private String publisherAddress;
    
    public Publisher() {
    }
    public Publisher(int publisherId,String publisherName,String publisherAddress) {
    	this.publisherId = publisherId;
    	this.publisherName = publisherName;
    	this.publisherAddress = publisherAddress;
    }
    
    public int getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public String getPublisherAddress() {
		return publisherAddress;
	}
	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}
	
}
