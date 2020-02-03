package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Sugang implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5623309535604336010L;

	private int portfolioid;
	private int memberid;
	
	
	public Sugang() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Sugang(int portfolioid, int memberid) {
		super();
		this.portfolioid = portfolioid;
		this.memberid = memberid;
	}

	
	@Override
	public String toString() {
		return "Sugang [portfolioid=" + portfolioid + ", memberid=" + memberid + "]";
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
