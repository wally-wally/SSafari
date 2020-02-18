package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Popular implements Serializable {
	private int locationid;
	private int categoryid;
	private String format;
	
	public Popular() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Popular(int locationid, int categoryid, String format) {
		super();
		this.locationid = locationid;
		this.categoryid = categoryid;
		this.format = format;
	}

	public int getLocationid() {
		return locationid;
	}

	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	public String toString() {
		return "Popular [locationid=" + locationid + ", categoryid=" + categoryid + ", format=" + format + "]";
	}


	
}
