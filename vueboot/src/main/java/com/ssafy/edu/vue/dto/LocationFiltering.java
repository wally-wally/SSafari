package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class LocationFiltering implements Serializable {

	/**
	 * 
	 */

	private int categoryid;
	private int locationid;

	public LocationFiltering() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocationFiltering(int categoryid, int locationid) {
		super();
		this.categoryid = categoryid;
		this.locationid = locationid;
	}

	@Override
	public String toString() {
		return "LocationFiltering [categoryid=" + categoryid + ", locationid=" + locationid + "]";
	}

	public int getBoardid() {
		return categoryid;
	}

	public void setBoardid(int categoryid) {
		this.categoryid = categoryid;
	}

	public int getLocationid() {
		return locationid;
	}

	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}

}
