package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Style implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5623309535604336010L;

	private int styleid;
	private int stylename;
	public Style() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Style(int styleid, int stylename) {
		super();
		this.styleid = styleid;
		this.stylename = stylename;
	}
	
	@Override
	public String toString() {
		return "Style [styleid=" + styleid + ", stylename=" + stylename + "]";
	}
	public int getstyleid() {
		return styleid;
	}
	public void setstyleid(int styleid) {
		this.styleid = styleid;
	}
	public int getstylename() {
		return stylename;
	}
	public void setstylename(int stylename) {
		this.stylename = stylename;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
