package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class CategoryPost implements Serializable {
	private int categoryid;
	private int memberid;
	public CategoryPost() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CategoryPost(int categoryid, int memberid) {
		super();
		this.categoryid = categoryid;
		this.memberid = memberid;
	}
	@Override
	public String toString() {
		return "CategoryPost [categoryid=" + categoryid + ", memberid=" + memberid + "]";
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	
	
}
