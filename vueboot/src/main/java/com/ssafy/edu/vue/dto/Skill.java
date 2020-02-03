package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Skill implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5623309535604336010L;

	private int skillid;
	private String skillname;
	public Skill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Skill(int skillid, String skillname) {
		super();
		this.skillid = skillid;
		this.skillname = skillname;
	}
	@Override
	public String toString() {
		return "Skill [skillid=" + skillid + ", skillname=" + skillname + "]";
	}
	public int getSkillid() {
		return skillid;
	}
	public void setSkillid(int skillid) {
		this.skillid = skillid;
	}
	public String getSkillname() {
		return skillname;
	}
	public void setSkillname(String skillname) {
		this.skillname = skillname;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
