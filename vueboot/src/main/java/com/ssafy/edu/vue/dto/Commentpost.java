package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Commentpost implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5623309535604336010L;

	private int cpostid;
	private int categoryid;
	private int postid;
	private int memberid;
	private String wdate;
	private String content;
	private String username;
	private int anonym;

	public Commentpost() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Commentpost(int cpostid, int postid, int memberid, String wdate, String content) {
		super();
		this.cpostid = cpostid;
		this.postid = postid;
		this.memberid = memberid;
		this.wdate = wdate;
		this.content = content;
	}

	public Commentpost(int postid, int memberid, String wdate, String content) {
		super();
		this.postid = postid;
		this.memberid = memberid;
		this.wdate = wdate;
		this.content = content;
	}
	
	public Commentpost(int cpostid, int postid, int memberid, String wdate, String content, String username) {
		super();
		this.cpostid = cpostid;
		this.postid = postid;
		this.memberid = memberid;
		this.wdate = wdate;
		this.content = content;
		this.username = username;
	}

	public Commentpost(int postid, int memberid, String content) {
		super();
		this.postid = postid;
		this.memberid = memberid;
		this.content = content;
	}
	
	public Commentpost(int postid, int memberid, String content, int anonym) {
		super();
		this.postid = postid;
		this.memberid = memberid;
		this.content = content;
		this.anonym = anonym;
	}

	public Commentpost(int cpostid, int postid, int memberid, String wdate, String content, String username,
			int anonym) {
		super();
		this.cpostid = cpostid;
		this.postid = postid;
		this.memberid = memberid;
		this.wdate = wdate;
		this.content = content;
		this.username = username;
		this.anonym = anonym;
	}
	
	

	public Commentpost(int cpostid, int categoryid, int postid, int memberid, String wdate, String content,
			String username, int anonym) {
		super();
		this.cpostid = cpostid;
		this.categoryid = categoryid;
		this.postid = postid;
		this.memberid = memberid;
		this.wdate = wdate;
		this.content = content;
		this.username = username;
		this.anonym = anonym;
	}

	public Commentpost(int cpostid, String content, int anonym) {
		super();
		this.cpostid = cpostid;
		this.content = content;
		this.anonym = anonym;
	}

	@Override
	public String toString() {
		return "Commentpost [cpostid=" + cpostid + ", postid=" + postid + ", memberid="
				+ memberid + ", wdate=" + wdate + ", content=" + content + "]";
	}

	public int getCpostid() {
		return cpostid;
	}

	public void setCpostid(int cpostid) {
		this.cpostid = cpostid;
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

	public int getMemberid() {
		return memberid;
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

	public int getpostid() {
		return postid;
	}

	public void setpostid(int postid) {
		this.postid = postid;
	}

	public int getAnonym() {
		return anonym;
	}

	public void setAnonym(int anonym) {
		this.anonym = anonym;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
