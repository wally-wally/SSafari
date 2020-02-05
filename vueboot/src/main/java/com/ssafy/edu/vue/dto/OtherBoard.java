package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class OtherBoard implements Serializable {
	private int id;
	private int boardid;
	private String title;
	private String body;
	private String created_at;
	private int memberid;
	private int anonymous;
	public OtherBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OtherBoard(int id, int boardid, String title, String body, String created_at, int memberid, int anonymous) {
		super();
		this.id = id;
		this.boardid = boardid;
		this.title = title;
		this.body = body;
		this.created_at = created_at;
		this.memberid = memberid;
		this.anonymous = anonymous;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBoardid() {
		return boardid;
	}
	public void setBoardid(int boardid) {
		this.boardid = boardid;
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
	public int getAnonymous() {
		return anonymous;
	}
	public void setAnonymous(int anonymous) {
		this.anonymous = anonymous;
	}
	@Override
	public String toString() {
		return "Otherboard [id=" + id + ", boardid=" + boardid + ", title=" + title + ", body=" + body + ", created_at="
				+ created_at + ", memberid=" + memberid + ", anonymous=" + anonymous + "]";
	}
	
}
