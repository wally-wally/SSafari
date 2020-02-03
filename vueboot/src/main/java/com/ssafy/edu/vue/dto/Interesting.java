package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Interesting implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5623309535604336010L;

	private int mid;
	private int skillid;
	public Interesting() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Interesting(int mid, int skillid) {
		super();
		this.mid = mid;
		this.skillid = skillid;
	}
	@Override
	public String toString() {
		return "Interesting [mid=" + mid + ", skillid=" + skillid + "]";
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getSkillid() {
		return skillid;
	}
	public void setSkillid(int skillid) {
		this.skillid = skillid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
