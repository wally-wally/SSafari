package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Teacher implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5623309535604336010L;

	private int tid;
	private int mid;
	private String nickname;
	private String gender;
	private String img;
	private String career;
	private String appeal;
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Teacher(int tid, int mid, String nickname, String gender, String img, String career, String appeal) {
		super();
		this.tid = tid;
		this.mid = mid;
		this.nickname = nickname;
		this.gender = gender;
		this.img = img;
		this.career = career;
		this.appeal = appeal;
	}
	public Teacher(int mid, String nickname, String gender, String img, String career, String appeal) {
		super();
		this.mid = mid;
		this.nickname = nickname;
		this.gender = gender;
		this.img = img;
		this.career = career;
		this.appeal = appeal;
	}
	@Override
	public String toString() {
		return "Teacher [tid=" + tid + ", mid=" + mid + ", nickname=" + nickname + ", gender=" + gender + ", img=" + img
				+ ", career=" + career + ", appeal=" + appeal + "]";
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getAppeal() {
		return appeal;
	}
	public void setAppeal(String appeal) {
		this.appeal = appeal;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
