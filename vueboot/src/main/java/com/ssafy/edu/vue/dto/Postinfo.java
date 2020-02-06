package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Postinfo implements Serializable {
	private int categoryid;
	private int postid;
	public Postinfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Postinfo(int categoryid, int postid) {
		super();
		this.categoryid = categoryid;
		this.postid = postid;
	}
	@Override
	public String toString() {
		return "Postinfo [categoryid=" + categoryid + ", postid=" + postid + "]";
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
}
