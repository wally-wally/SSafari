package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class LocationFiltering implements Serializable {

	/**
	 * 
	 */

	private int categoryid;
	private int locationid;
	private int memberid;

	public LocationFiltering() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocationFiltering(int categoryid, int locationid) {
		super();
		this.categoryid = categoryid;
		this.locationid = locationid;
	}
	
	public LocationFiltering(int categoryid, int locationid, int memberid) {
		super();
		this.categoryid = categoryid;
		this.locationid = locationid;
		this.memberid = memberid;
	}

	@Override
	public String toString() {
		return "LocationFiltering [categoryid=" + categoryid + ", locationid=" + locationid + ", memberid=" + memberid
				+ "]";
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public int getMemberid() {
		return memberid;
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}

	public int getLocationid() {
		return locationid;
	}

	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}

}
