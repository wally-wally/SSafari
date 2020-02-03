package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Commentportfolio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5623309535604336010L;

	private int cportfolioid;
	private int portfolioid;
	private int memberid;
	private String wdate;
	private String content;
	private String username;

	public Commentportfolio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Commentportfolio(int cportfolioid, int portfolioid, int memberid, String wdate, String content) {
		super();
		this.cportfolioid = cportfolioid;
		this.portfolioid = portfolioid;
		this.memberid = memberid;
		this.wdate = wdate;
		this.content = content;
	}

	public Commentportfolio(int portfolioid, int memberid, String wdate, String content) {
		super();
		this.portfolioid = portfolioid;
		this.memberid = memberid;
		this.wdate = wdate;
		this.content = content;
	}

	public Commentportfolio(int portfolioid, int memberid, String content) {
		super();
		this.portfolioid = portfolioid;
		this.memberid = memberid;
		this.content = content;
	}
	
	public Commentportfolio(int cportfolioid, int portfolioid, int memberid, String wdate, String content,
			String username) {
		super();
		this.cportfolioid = cportfolioid;
		this.portfolioid = portfolioid;
		this.memberid = memberid;
		this.wdate = wdate;
		this.content = content;
		this.username = username;
	}

	@Override
	public String toString() {
		return "Commentportfolio [cportfolioid=" + cportfolioid + ", portfolioid=" + portfolioid + ", memberid="
				+ memberid + ", wdate=" + wdate + ", content=" + content + "]";
	}

	public int getCportfolioid() {
		return cportfolioid;
	}

	public void setCportfolioid(int cportfolioid) {
		this.cportfolioid = cportfolioid;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
