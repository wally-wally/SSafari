package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Post implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5623309535604336010L;

	private int postid;
	private String title;
	private String body;
	private String created_at;
	private int memberid;
	private String username;

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	public Post(String title, String body, int memberid) {
		super();
		this.title = title;
		this.body = body;
		this.memberid = memberid;
	}




	public Post(int postid, String title, String body) {
		super();
		this.postid = postid;
		this.title = title;
		this.body = body;
	}




	public Post(int postid, String title, String body, int memberid) {
		super();
		this.postid = postid;
		this.title = title;
		this.body = body;
		this.memberid = memberid;
	}




	public Post(int postid, String title, String body, String created_at, int memberid) {
		super();
		this.postid = postid;
		this.title = title;
		this.body = body;
		this.created_at = created_at;
		this.memberid = memberid;
	}




	public Post(String title, String body, String created_at, int memberid) {
		super();
		this.title = title;
		this.body = body;
		this.created_at = created_at;
		this.memberid = memberid;
	}




	public Post(int postid, String title, String body, String created_at) {
		super();
		this.postid = postid;
		this.title = title;
		this.body = body;
		this.created_at = created_at;
	}
	
	
	

	public Post(int postid, String title, String body, String created_at, String username) {
		super();
		this.postid = postid;
		this.title = title;
		this.body = body;
		this.created_at = created_at;
		this.username = username;
	}




	public Post(int postid, String title, String body, String created_at, int memberid, String username) {
		super();
		this.postid = postid;
		this.title = title;
		this.body = body;
		this.created_at = created_at;
		this.memberid = memberid;
		this.username = username;
	}




	public Post(String title, String body, String created_at) {
		super();
		this.title = title;
		this.body = body;
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "Post [postid=" + postid + ", title=" + title + ", body=" + body + ", created_at=" + created_at + "]";
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public int getMemberid() {
		return memberid;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}

	

}
