package com.ssafy.edu.vue.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ssafy.edu.vue.dto.Member;

public interface IJwtService {

	String create(String key, Member data, String subject);

	boolean isUsable(String jwt);

	String signin(Member data);

//	Map<String, Object> get(String key);

	Map<String, Object> get(String key, HttpServletRequest req);

}
