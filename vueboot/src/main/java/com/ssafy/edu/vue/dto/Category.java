package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Category implements Serializable {
	private int id;
	private String name;
	private String explanation;
	private int memberid;
	private String username;
	private int flag;
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(String name, String explanation) {
		super();
		this.name = name;
		this.explanation = explanation;
	}
	
	public Category(int id, int flag) {
		super();
		this.id = id;
		this.flag = flag;
	}
	
	public Category(String name, String explanation, int memberid) {
		super();
		this.name = name;
		this.explanation = explanation;
		this.memberid = memberid;
	}
	
	public Category(int id, String name, String explanation, int memberid) {
		super();
		this.id = id;
		this.name = name;
		this.explanation = explanation;
		this.memberid = memberid;
	}
	
	public Category(int id, String name, String explanation, int memberid, String username) {
		super();
		this.id = id;
		this.name = name;
		this.explanation = explanation;
		this.memberid = memberid;
		this.username = username;
	}
	
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", explanation=" + explanation + ", memberid=" + memberid
				+ ", username=" + username + ", flag=" + flag + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
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
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
}
