package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class InfoCount implements Serializable {
	private int admin;
	private int ssafy;
	private int user;
	private int post;
	private int study;
	
	public InfoCount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InfoCount(int admin, int ssafy, int user, int post, int study) {
		super();
		this.admin = admin;
		this.ssafy = ssafy;
		this.user = user;
		this.post = post;
		this.study = study;
	}

	@Override
	public String toString() {
		return "InfoCount [admin=" + admin + ", ssafy=" + ssafy + ", user=" + user + ", post=" + post + ", study="
				+ study + "]";
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public int getSsafy() {
		return ssafy;
	}

	public void setSsafy(int ssafy) {
		this.ssafy = ssafy;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getPost() {
		return post;
	}

	public void setPost(int post) {
		this.post = post;
	}

	public int getStudy() {
		return study;
	}

	public void setStudy(int study) {
		this.study = study;
	}
	
	
	
}
