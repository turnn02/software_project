package com.mkyong.persistence;

public class UserPost {
	
	private int id;
	
	private User user;
	
	private String post;
	
	private String productName;

	public UserPost(int id, User user, String post, String productName) {
		this.id = id;
		this.user = user;
		this.post = post;
		this.productName = productName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
