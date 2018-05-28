package com.mkyong.common;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.mkyong.persistence.DB;
import com.mkyong.persistence.UserPost;

@ManagedBean
public class HomeBean {
	
	private List<UserPost> postList;
	
	
	@PostConstruct
	public void init() {
		postList = DB.getPosts(null);
	}

	public List<UserPost> getPostList() {
		return postList;
	}

	public void setPostList(List<UserPost> postList) {
		this.postList = postList;
	}

}
