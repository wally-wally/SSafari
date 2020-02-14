package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class LikePost implements Serializable {
	private int categoryid;
	private int id;
	private String title;
	private int memberid;
	private String created_at;
	public LikePost() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LikePost(int categoryid, int id, String title, int memberid, String created_at) {
		super();
		this.categoryid = categoryid;
		this.id = id;
		this.title = title;
		this.memberid = memberid;
		this.created_at = created_at;
	}
	public LikePost(int categoryid, int id, String title, int memberid) {
		super();
		this.categoryid = categoryid;
		this.id = id;
		this.title = title;
		this.memberid = memberid;
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
}
