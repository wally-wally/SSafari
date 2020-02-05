package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Facebook implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5623309535604336010L;

	private String email;
	private String id;
	private String name;
	
	public Facebook() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Facebook [email=" + email + ", id=" + id + ", name=" + name + "]";
	}

	public Facebook(String email, String id, String name) {
		super();
		this.email = email;
		this.id = id;
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
