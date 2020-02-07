package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Category implements Serializable {
	private String name;
	private String explanation;
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(String name, String explanation) {
		super();
		this.name = name;
		this.explanation = explanation;
	}
	@Override
	public String toString() {
		return "Category [name=" + name + ", explanation=" + explanation + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
}
