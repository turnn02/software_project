package com.mkyong.common;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.mkyong.persistence.DB;
import com.mkyong.persistence.UserProduct;

@ManagedBean
public class MyProductsBean extends AbstractBean {
	
	private String product;
	
	private List<UserProduct> productList;
	
	@PostConstruct
	public void init() {
		productList = DB.getProducts(sessionDataBean.getUser());
	}
	
	public void send() {
		
		if (product == null || product == "") {
			sendWarnMessage("Product could not be empty", null);
			return;
		}
		
		DB.sendProduct(sessionDataBean.getUser(), product);
		
		sendInfoMessage("Succesfuly added", "Succesfuly added");
		product = null;
		productList = DB.getProducts(sessionDataBean.getUser());
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String text) {
		this.product = text;
	}

	public List<UserProduct> getProductList() {
		return productList;
	}

	public void setProductList(List<UserProduct> productList) {
		this.productList = productList;
	}

}
