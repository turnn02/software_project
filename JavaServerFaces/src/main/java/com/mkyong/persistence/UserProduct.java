package com.mkyong.persistence;

public class UserProduct {
	
	private int id;
	
	private User user;
	
	private String product;

	public UserProduct(int id, User user, String product) {
		this.id = id;
		this.user = user;
		this.product = product;
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

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
}
