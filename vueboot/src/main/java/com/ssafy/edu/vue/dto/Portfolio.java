package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Portfolio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5623309535604336010L;

	private int portfolioid;
	private String title;
	private String body;
	private String img;
	private String created_at;
	private int memberid;
	private String username;
	private int commentcount;
	private int capacity;
	private int applicant;
	private String startdate;
	private String enddate;
	private int price;
	private String location;

	public Portfolio() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public int getMemberid() {
		return memberid;
	}



	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}



	public Portfolio(String title, String body, String img, String created_at, int memberid) {
		super();
		this.title = title;
		this.body = body;
		this.img = img;
		this.created_at = created_at;
		this.memberid = memberid;
	}



	public Portfolio(String title, String body, String img, int memberid) {
		super();
		this.title = title;
		this.body = body;
		this.img = img;
		this.memberid = memberid;
	}

	


	public Portfolio(int portfolioid, String title, String body, String img) {
		super();
		this.portfolioid = portfolioid;
		this.title = title;
		this.body = body;
		this.img = img;
	}



	public Portfolio(int portfolioid, String title, String body, String img, String created_at, int memberid) {
		super();
		this.portfolioid = portfolioid;
		this.title = title;
		this.body = body;
		this.img = img;
		this.created_at = created_at;
		this.memberid = memberid;
	}



	public Portfolio(int portfolioid, String title, String body, String img, String created_at) {
		super();
		this.portfolioid = portfolioid;
		this.title = title;
		this.body = body;
		this.img = img;
		this.created_at = created_at;
	}

	
	public Portfolio(int portfolioid, String title, String body, String img, String created_at, String username) {
		super();
		this.portfolioid = portfolioid;
		this.title = title;
		this.body = body;
		this.img = img;
		this.created_at = created_at;
		this.username = username;
	}
	
	



	public Portfolio(int portfolioid, String title, String body, String img, String created_at, int memberid,
			String username) {
		super();
		this.portfolioid = portfolioid;
		this.title = title;
		this.body = body;
		this.img = img;
		this.created_at = created_at;
		this.memberid = memberid;
		this.username = username;
	}



	

	public Portfolio(int portfolioid, String title, String body, String img, String created_at, int memberid,
			String username, int commentcount) {
		super();
		this.portfolioid = portfolioid;
		this.title = title;
		this.body = body;
		this.img = img;
		this.created_at = created_at;
		this.memberid = memberid;
		this.username = username;
		this.commentcount = commentcount;
	}
	
	


	public Portfolio(int portfolioid, String title, String body, String img, String created_at, int memberid,
			String username, int commentcount, int capacity, int applicant, String startdate, String enddate, int price,
			String location) {
		super();
		this.portfolioid = portfolioid;
		this.title = title;
		this.body = body;
		this.img = img;
		this.created_at = created_at;
		this.memberid = memberid;
		this.username = username;
		this.commentcount = commentcount;
		this.capacity = capacity;
		this.applicant = applicant;
		this.startdate = startdate;
		this.enddate = enddate;
		this.price = price;
		this.location = location;
	}



	public Portfolio(int portfolioid, String title, String body, String img, String created_at, int memberid,
			String username, int commentcount, int capacity, String startdate, String enddate, int price,
			String location) {
		super();
		this.portfolioid = portfolioid;
		this.title = title;
		this.body = body;
		this.img = img;
		this.created_at = created_at;
		this.memberid = memberid;
		this.username = username;
		this.commentcount = commentcount;
		this.capacity = capacity;
		this.startdate = startdate;
		this.enddate = enddate;
		this.price = price;
		this.location = location;
	}



	@Override
	public String toString() {
		return "Portfolio [portfolioid=" + portfolioid + ", title=" + title + ", body=" + body + ", img=" + img
				+ ", created_at=" + created_at + ", memberid=" + memberid + ", username=" + username + ", commentcount="
				+ commentcount + "]";
	}



	public int getPortfolioid() {
		return portfolioid;
	}

	public void setPortfolioid(int portfolioid) {
		this.portfolioid = portfolioid;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public int getCommentcount() {
		return commentcount;
	}



	public void setCommentcount(int commentcount) {
		this.commentcount = commentcount;
	}

	

	public int getCapacity() {
		return capacity;
	}



	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}



	public String getStartdate() {
		return startdate;
	}



	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}



	public String getEnddate() {
		return enddate;
	}



	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}

	

	public int getApplicant() {
		return applicant;
	}



	public void setApplicant(int applicant) {
		this.applicant = applicant;
	}

	

	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	

}
