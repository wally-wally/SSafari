package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class LocationFiltering implements Serializable {

	/**
	 * 
	 */

	private int boardid;
	private int locationid;

	public LocationFiltering() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocationFiltering(int boardid, int locationid) {
		super();
		this.boardid = boardid;
		this.locationid = locationid;
	}

	@Override
	public String toString() {
		return "LocationFiltering [boardid=" + boardid + ", locationid=" + locationid + "]";
	}

	public int getBoardid() {
		return boardid;
	}

	public void setBoardid(int boardid) {
		this.boardid = boardid;
	}

	public int getLocationid() {
		return locationid;
	}

	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}

}
