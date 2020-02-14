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
	private int categoryid;
	private int anonymous;
	private int locationid;
	private String img;
	private int likes;
	private int comments;

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Post(int postid, String title, String body, String created_at, int memberid, String username, int categoryid,
			int anonymous, int locationid, String img) {
		super();
		this.postid = postid;
		this.title = title;
		this.body = body;
		this.created_at = created_at;
		this.memberid = memberid;
		this.username = username;
		this.categoryid = categoryid;
		this.anonymous = anonymous;
		this.locationid = locationid;
		this.img = img;
	}



	public Post(String title, String body, int memberid, int categoryid, int anonymous, int locationid) {
		super();
		this.title = title;
		this.body = body;
		this.memberid = memberid;
		this.categoryid = categoryid;
		this.anonymous = anonymous;
		this.locationid = locationid;
	}

	public Post(int postid, String title, String body) {
		super();
		this.postid = postid;
		this.title = title;
		this.body = body;
	}
	
	public Post(int postid, String title, String body, String created_at, int memberid, String username, int categoryid,
			int anonymous, int locationid) {
		super();
		this.postid = postid;
		this.title = title;
		this.body = body;
		this.created_at = created_at;
		this.memberid = memberid;
		this.username = username;
		this.categoryid = categoryid;
		this.anonymous = anonymous;
		this.locationid = locationid;
	}

	public Post(int postid, String title, String body, String created_at, int memberid, String username, int categoryid,
			int anonymous, int locationid, String img, int likes, int comments) {
		super();
		this.postid = postid;
		this.title = title;
		this.body = body;
		this.created_at = created_at;
		this.memberid = memberid;
		this.username = username;
		this.categoryid = categoryid;
		this.anonymous = anonymous;
		this.locationid = locationid;
		this.likes = likes;
		this.comments = comments;
	}



	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
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

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public int getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(int anonymous) {
		this.anonymous = anonymous;
	}

	public int getLocationid() {
		return locationid;
	}

	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Post [postid=" + postid + ", title=" + title + ", body=" + body + ", created_at=" + created_at
				+ ", memberid=" + memberid + ", username=" + username + ", categoryid=" + categoryid + ", anonymous="
				+ anonymous + ", locationid=" + locationid + "]";
	}

}
