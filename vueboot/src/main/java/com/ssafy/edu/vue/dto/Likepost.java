package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Likepost implements Serializable {
	private int postid;
	private int memberid;
	public Likepost() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Likepost(int postid, int memberid) {
		super();
		this.postid = postid;
		this.memberid = memberid;
	}
	@Override
	public String toString() {
		return "Likepost [postid=" + postid + ", memberid=" + memberid + "]";
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
}
