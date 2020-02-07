package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Jmt implements Serializable {
	private int id;
	private String name;
	private String location;
	private String body;
	private String created_at;
	private int memberid;
	private String username;
	private int anonymous;
	public Jmt() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Jmt(int id, String name, String location, String body, String created_at, int memberid, String username,
			int anonymous) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.body = body;
		this.created_at = created_at;
		this.memberid = memberid;
		this.username = username;
		this.anonymous = anonymous;
	}
	public Jmt(String name, String location, String body, int memberid, int anonymous) {
		super();
		this.name = name;
		this.location = location;
		this.body = body;
		this.memberid = memberid;
		this.anonymous = anonymous;
	}
	public Jmt(String name, String location, String body, int anonymous) {
		super();
		this.name = name;
		this.location = location;
		this.body = body;
		this.anonymous = anonymous;
	}
	@Override
	public String toString() {
		return "Jmt [id=" + id + ", name=" + name + ", location=" + location + ", body=" + body + ", created_at="
				+ created_at + ", memberid=" + memberid + ", username=" + username + ", anonymous=" + anonymous + "]";
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	public int getAnonymous() {
		return anonymous;
	}
	public void setAnonymous(int anonymous) {
		this.anonymous = anonymous;
	}
	
}
