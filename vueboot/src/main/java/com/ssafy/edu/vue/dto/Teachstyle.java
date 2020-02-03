package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Teachstyle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5623309535604336010L;

	private int tid;
	private int sid;
	public Teachstyle() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Teachstyle(int tid, int sid) {
		super();
		this.tid = tid;
		this.sid = sid;
	}
	@Override
	public String toString() {
		return "Teachstyle [tid=" + tid + ", sid=" + sid + "]";
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
