package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Review implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5623309535604336010L;

	private int reviewid;
	private int lid;
	private int mid;
	private int rating;
	private String content;
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Review(int reviewid, int lid, int mid, int rating, String content) {
		super();
		this.reviewid = reviewid;
		this.lid = lid;
		this.mid = mid;
		this.rating = rating;
		this.content = content;
	}
	public Review(int lid, int mid, int rating, String content) {
		super();
		this.lid = lid;
		this.mid = mid;
		this.rating = rating;
		this.content = content;
	}
	@Override
	public String toString() {
		return "Review [reviewid=" + reviewid + ", lid=" + lid + ", mid=" + mid + ", rating=" + rating + ", content="
				+ content + "]";
	}
	public int getReviewid() {
		return reviewid;
	}
	public void setReviewid(int reviewid) {
		this.reviewid = reviewid;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
