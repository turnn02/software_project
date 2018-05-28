package com.mkyong.common;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.mkyong.persistence.DB;
import com.mkyong.persistence.UserPost;
import com.mkyong.persistence.UserProduct;

@ManagedBean
public class MyPageBean extends AbstractBean {
	
	private String post;
	
	private List<UserPost> postList;
	
	private List<String> products;
	
	private String selectedProduct;
	
	@PostConstruct
	public void init() {
		postList = DB.getPosts(sessionDataBean.getUser());
		List<UserProduct> list = DB.getProducts(sessionDataBean.getUser());
		products = new ArrayList<String>();
		for (UserProduct userProduct : list) {
			products.add(userProduct.getProduct());
		}
	}
	
	public void send() {
		
		if (post == null || post == "") {
			sendWarnMessage("Post could not be empty", null);
			return;
		}
		
		if (selectedProduct == null || selectedProduct == "") {
			sendWarnMessage("Please select a product", null);
			return;
		}
		
		DB.sendPost(sessionDataBean.getUser(), post, selectedProduct);
		
		sendInfoMessage("Succesfuly sent", "Succesfuly sent");
		post = null;
		postList = DB.getPosts(sessionDataBean.getUser());
	}

	public String getPost() {
		return post;
	}

	public void setPost(String text) {
		this.post = text;
	}

	public List<UserPost> getPostList() {
		return postList;
	}

	public void setPostList(List<UserPost> postList) {
		this.postList = postList;
	}

	public List<String> getProducts() {
		return products;
	}

	public void setProducts(List<String> products) {
		this.products = products;
	}

	public String getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(String selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

}
