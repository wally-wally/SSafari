package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class AuthRequest implements Serializable {
	private int memberid;
	private String name;
	private String img;
	private int locationid;
	private String location;
	private int unit;
	private int flag;
	
	public AuthRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AuthRequest(int memberid, String img, int locationid, int unit) {
		super();
		this.memberid = memberid;
		this.img = img;
		this.locationid = locationid;
		this.unit = unit;
	}
	public AuthRequest(String img, int locationid, int unit) {
		super();
		this.img = img;
		this.locationid = locationid;
		this.unit = unit;
	}
	
	public AuthRequest(int memberid, int locationid, int unit) {
		super();
		this.memberid = memberid;
		this.locationid = locationid;
		this.unit = unit;
	}

	public AuthRequest(int memberid, int flag) {
		super();
		this.memberid = memberid;
		this.flag = flag;
	}
	
	public AuthRequest(int memberid, String name, String img, int locationid, String location, int unit) {
		super();
		this.memberid = memberid;
		this.name = name;
		this.img = img;
		this.locationid = locationid;
		this.location = location;
		this.unit = unit;
	}

	public AuthRequest(int memberid, String name, String img, int locationid, String location, int unit, int flag) {
		super();
		this.memberid = memberid;
		this.name = name;
		this.img = img;
		this.locationid = locationid;
		this.location = location;
		this.unit = unit;
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "AuthRequest [memberid=" + memberid + ", name=" + name + ", img=" + img + ", locationid=" + locationid
				+ ", location=" + location + ", unit=" + unit + ", flag=" + flag + "]";
	}

	public int getLocationid() {
		return locationid;
	}
	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
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

	
	
}
