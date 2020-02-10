package com.ssafy.edu.vue.dto;

import java.io.Serializable;

public class Member implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5623309535604336010L;

	private int memberid;
	private String email;
	private String password;
	private String name;
	private String username;
	private String signupdate;
	private int delflag;
	private int auth;
	private String githubid;
	private int locationid;
	private int unit;
	private int social;
	private boolean signup;
	private String message;
	
	

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Member(String email, String password, String name, String username) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.username = username;
	}



	public Member(String email, String password, String name, String username, int social) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.username = username;
		this.social = social;
	}



	public Member(int memberid, String email, String name, String username, String signupdate, int auth,
			String githubid, int locationid, int unit, int social) {
		super();
		this.memberid = memberid;
		this.email = email;
		this.name = name;
		this.username = username;
		this.signupdate = signupdate;
		this.auth = auth;
		this.githubid = githubid;
		this.locationid = locationid;
		this.unit = unit;
		this.social = social;
	}



	public Member(int memberid, String username, String githubid) {
		super();
		this.memberid = memberid;
		this.username = username;
		this.githubid = githubid;
	}



	public Member(int memberid, String password, String username, String githubid) {
		super();
		this.memberid = memberid;
		this.password = password;
		this.username = username;
		this.githubid = githubid;
	}



	public Member(int memberid, String email, String password, String name, String username, String signupdate,
			int delflag, int auth) {
		super();
		this.memberid = memberid;
		this.email = email;
		this.password = password;
		this.name = name;
		this.username = username;
		this.signupdate = signupdate;
		this.delflag = delflag;
		this.auth = auth;
	}



	public Member(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public Member(String email, String name, String username) {
		super();
		this.email = email;
		this.name = name;
		this.username = username;
	}


	
	public Member(int memberid, String password) {
		super();
		this.memberid = memberid;
		this.password = password;
	}



	public Member(String email, String password, String name, String username, String signupdate) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.username = username;
		this.signupdate = signupdate;
	}



	public Member(int memberid, String email, String name, String username, String signupdate, int auth) {
		super();
		this.memberid = memberid;
		this.email = email;
		this.name = name;
		this.username = username;
		this.signupdate = signupdate;
		this.auth = auth;
	}
	
	




	public Member(int memberid, String email, String name, String username, int auth) {
		super();
		this.memberid = memberid;
		this.email = email;
		this.name = name;
		this.username = username;
		this.auth = auth;
	}



	public Member(int memberid, String email, String name, String username, String signupdate, int auth,
			String githubid) {
		super();
		this.memberid = memberid;
		this.email = email;
		this.name = name;
		this.username = username;
		this.signupdate = signupdate;
		this.auth = auth;
		this.githubid = githubid;
	}



	public Member(boolean signup, String message) {
		super();
		this.signup = signup;
		this.message = message;
	}
	
	



	public Member(int memberid, String email, String password, String name, String username, String signupdate,
			int auth, String githubid, int locationid, int unit) {
		super();
		this.memberid = memberid;
		this.email = email;
		this.password = password;
		this.name = name;
		this.username = username;
		this.signupdate = signupdate;
		this.auth = auth;
		this.githubid = githubid;
		this.locationid = locationid;
		this.unit = unit;
	}






	public Member(int memberid, String email, String password, String name, String username, String signupdate,
			int delflag, int auth, String githubid, int locationid, int unit, boolean signup, String message) {
		super();
		this.memberid = memberid;
		this.email = email;
		this.password = password;
		this.name = name;
		this.username = username;
		this.signupdate = signupdate;
		this.delflag = delflag;
		this.auth = auth;
		this.githubid = githubid;
		this.locationid = locationid;
		this.unit = unit;
		this.signup = signup;
		this.message = message;
	}



	

	public Member(int memberid, String email, String password, String name, String username, String signupdate,
			int delflag, int auth, String githubid, int locationid, int unit, int social, boolean signup,
			String message) {
		super();
		this.memberid = memberid;
		this.email = email;
		this.password = password;
		this.name = name;
		this.username = username;
		this.signupdate = signupdate;
		this.delflag = delflag;
		this.auth = auth;
		this.githubid = githubid;
		this.locationid = locationid;
		this.unit = unit;
		this.social = social;
		this.signup = signup;
		this.message = message;
	}



	@Override
	public String toString() {
		return "Member [memberid=" + memberid + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", username=" + username + ", signupdate=" + signupdate + ", delflag=" + delflag + ", auth=" + auth
				+ ", githubid=" + githubid + ", locationid=" + locationid + ", unit=" + unit + ", social=" + social
				+ ", signup=" + signup + ", message=" + message + "]";
	}



	public int getMemberid() {
		return memberid;
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}



	public String getSignupdate() {
		return signupdate;
	}

	public void setSignupdate(String signupdate) {
		this.signupdate = signupdate;
	}

	public int getDelflag() {
		return delflag;
	}

	public void setDelflag(int delflag) {
		this.delflag = delflag;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public String getGithubid() {
		return githubid;
	}



	public void setGithubid(String githubid) {
		this.githubid = githubid;
	}



	public boolean isSignup() {
		return signup;
	}



	public void setSignup(boolean signup) {
		this.signup = signup;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
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



	public int getSocial() {
		return social;
	}



	public void setSocial(int social) {
		this.social = social;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
