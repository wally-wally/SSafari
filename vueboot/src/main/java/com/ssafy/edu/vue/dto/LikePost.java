package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class LikePost implements Serializable {
	private int postid;
	private String title;
	private int categoryid;
	private String categoryname;
	private int memberid;
	private String username;
	private int anonymous;
	private String created_at;
	public LikePost() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LikePost(int postid, String title, int categoryid, String categoryname, int memberid, String username,
			int anonymous, String created_at) {
		super();
		this.postid = postid;
		this.title = title;
		this.categoryid = categoryid;
		this.categoryname = categoryname;
		this.memberid = memberid;
		this.username = username;
		this.anonymous = anonymous;
		this.created_at = created_at;
	}
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAnonymous() {
		return anonymous;
	}
	public void setAnonymous(int anonymous) {
		this.anonymous = anonymous;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "LikePost [postid=" + postid + ", title=" + title + ", categoryid=" + categoryid + ", categoryname="
				+ categoryname + ", memberid=" + memberid + ", username=" + username + ", anonymous=" + anonymous
				+ ", created_at=" + created_at + "]";
	}
	
	
	
}
