package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Sugang implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5623309535604336010L;

	private int portfolioid;
	private int memberid;
	private String username;
	
	
	public Sugang() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Sugang(int portfolioid, int memberid) {
		super();
		this.portfolioid = portfolioid;
		this.memberid = memberid;
	}

	
	public Sugang(int portfolioid, int memberid, String username) {
		super();
		this.portfolioid = portfolioid;
		this.memberid = memberid;
		this.username = username;
	}


	


	@Override
	public String toString() {
		return "Sugang [portfolioid=" + portfolioid + ", memberid=" + memberid + ", username=" + username + "]";
	}


	public int getPortfolioid() {
		return portfolioid;
	}


	public void setPortfolioid(int portfolioid) {
		this.portfolioid = portfolioid;
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
