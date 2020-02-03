package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Lecture implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5623309535604336010L;

	private int lid;
	private int tid;
	private String title;
	private String location;
	private int price;
	private int capacity;
	private int applicant;
	private String introduction;
	private String curriculum;
	private String period;
	public Lecture() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Lecture(int lid, int tid, String title, String location, int price, int capacity, int applicant,
			String introduction, String curriculum, String period) {
		super();
		this.lid = lid;
		this.tid = tid;
		this.title = title;
		this.location = location;
		this.price = price;
		this.capacity = capacity;
		this.applicant = applicant;
		this.introduction = introduction;
		this.curriculum = curriculum;
		this.period = period;
	}
	public Lecture(int tid, String title, String location, int price, int capacity, int applicant,
			String introduction, String curriculum, String period) {
		super();
		this.tid = tid;
		this.title = title;
		this.location = location;
		this.price = price;
		this.capacity = capacity;
		this.applicant = applicant;
		this.introduction = introduction;
		this.curriculum = curriculum;
		this.period = period;
	}
	@Override
	public String toString() {
		return "Interesting [lid=" + lid + ", tid=" + tid + ", title=" + title + ", location=" + location + ", price="
				+ price + ", capacity=" + capacity + ", applicant=" + applicant + ", introduction=" + introduction
				+ ", curriculum=" + curriculum + ", period=" + period + "]";
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getApplicant() {
		return applicant;
	}
	public void setApplicant(int applicant) {
		this.applicant = applicant;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getCurriculum() {
		return curriculum;
	}
	public void setCurriculum(String curriculum) {
		this.curriculum = curriculum;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
