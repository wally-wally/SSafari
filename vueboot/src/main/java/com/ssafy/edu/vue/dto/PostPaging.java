package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class PostPaging implements Serializable {
	private int categoryid;
	private String keyword;
	private int page;
	public PostPaging() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PostPaging(int categoryid, String keyword, int page) {
		super();
		this.categoryid = categoryid;
		this.keyword = keyword;
		this.page = page;
	}
	@Override
	public String toString() {
		return "PostPaging [categoryid=" + categoryid + ", keyword=" + keyword + ", page=" + page + "]";
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
}
