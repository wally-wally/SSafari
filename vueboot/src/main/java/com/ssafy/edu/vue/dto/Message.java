package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Message implements Serializable {
	private int id;
	private int fromid;
	private int toid;
	private String username;
	private String title;
	private String content;
	private String created_at;
	private int isread;
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Message(int id, int fromid, int toid, String title, String content, String created_at) {
		super();
		this.id = id;
		this.fromid = fromid;
		this.toid = toid;
		this.title = title;
		this.content = content;
		this.created_at = created_at;
	}
	public Message(int id, int fromid, int toid, String title, String content, String created_at, int isread) {
		super();
		this.id = id;
		this.fromid = fromid;
		this.toid = toid;
		this.title = title;
		this.content = content;
		this.created_at = created_at;
		this.isread = isread;
	}
	
	public Message(int id, int fromid, int toid, String username, String title, String content, String created_at) {
		super();
		this.id = id;
		this.fromid = fromid;
		this.toid = toid;
		this.username = username;
		this.title = title;
		this.content = content;
		this.created_at = created_at;
	}
	public Message(int id, int fromid, int toid, String username, String title, String content, String created_at,
			int isread) {
		super();
		this.id = id;
		this.fromid = fromid;
		this.toid = toid;
		this.username = username;
		this.title = title;
		this.content = content;
		this.created_at = created_at;
		this.isread = isread;
	}
	
	public Message(int fromid, int toid, String title, String content) {
		super();
		this.fromid = fromid;
		this.toid = toid;
		this.title = title;
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFromid() {
		return fromid;
	}
	public void setFromid(int fromid) {
		this.fromid = fromid;
	}
	public int getToid() {
		return toid;
	}
	public void setToid(int toid) {
		this.toid = toid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
	public int getIsread() {
		return isread;
	}
	public void setIsread(int isread) {
		this.isread = isread;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", fromid=" + fromid + ", toid=" + toid + ", username=" + username + ", title="
				+ title + ", content=" + content + ", created_at=" + created_at + ", isread=" + isread + "]";
	}
	
	
	
}
