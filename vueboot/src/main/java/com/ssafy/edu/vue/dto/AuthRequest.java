package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class AuthRequest implements Serializable {
	private int memberid;
	private String img;
	public AuthRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthRequest(int memberid, String img) {
		super();
		this.memberid = memberid;
		this.img = img;
	}
	@Override
	public String toString() {
		return "AuthRequest [memberid=" + memberid + ", img=" + img + "]";
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}

	
	
}
