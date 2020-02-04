package com.ssafy.edu.vue.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssafy.edu.vue.controller.MemberController;
import com.ssafy.edu.vue.controller.UnauthorizedException;
import com.ssafy.edu.vue.dto.Member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Service("jwtService")
public class JwtServiceImpl implements IJwtService{

	private static final String SALT =  "A5isTheBest";
	public static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);
	
	
	@Override
	public String signin(Member data){
		String jwt = Jwts.builder()
						 .setHeaderParam("typ", "JWT")
						 .setHeaderParam("regDate", System.currentTimeMillis())
						 .setExpiration(new Date(System.currentTimeMillis() + 1000 * 5 * 60 * 60 ))
						 .setSubject("로그인토큰")
						 .claim("access-Token", data)
						 .signWith(SignatureAlgorithm.HS256, this.generateKey())
						 .compact();
		return jwt;
	}	
	
	@Override
	public String create(String key, Member data, String subject){
		String jwt = Jwts.builder()
						 .setHeaderParam("typ", "JWT")
						 .setHeaderParam("regDate", System.currentTimeMillis())
						 .setSubject(subject)
						 .claim(key, data)
						 .signWith(SignatureAlgorithm.HS256, this.generateKey())
						 .compact();
		return jwt;
	}	

	private byte[] generateKey(){
		byte[] key = null;
		try {
			key = SALT.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			if(logger.isInfoEnabled()){
				e.printStackTrace();
			}else{
				logger.error("Making JWT Key Error ::: {}", e.getMessage());
			}
		}
		
		return key;
	}
	
	@Override
	public boolean isUsable(String jwt) {
		try{
			Jws<Claims> claims = Jwts.parser()
					  .setSigningKey(this.generateKey())
					  .parseClaimsJws(jwt);
			return true;
			
		}catch (Exception e) {
			throw new UnauthorizedException();
		}
	}
	
	@Override
	public Map<String, Object> get(String key, HttpServletRequest req) {
		HttpServletRequest request = req;
		String jwt = request.getHeader("Authorization");
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser()
						 .setSigningKey(SALT.getBytes("UTF-8"))
						 .parseClaimsJws("eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNTgwNzg0MTA3NTgyLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1ODA4MDIxMDcsInN1YiI6IuuhnOq3uOyduO2GoO2BsCIsImFjY2Vzcy1Ub2tlbiI6eyJtZW1iZXJpZCI6MCwiZW1haWwiOiJkZWxpZ2h0X2pvb0BuYXZlci5jb20iLCJwYXNzd29yZCI6IjEyMzQiLCJuYW1lIjpudWxsLCJ1c2VybmFtZSI6bnVsbCwic2lnbnVwZGF0ZSI6bnVsbCwiZGVsZmxhZyI6MCwiYXV0aCI6MCwiZ2l0aHViaWQiOm51bGwsInNpZ251cCI6ZmFsc2UsIm1lc3NhZ2UiOm51bGx9fQ.cGDEO1eXIRxphAM9H4n8XoZGIOHcyk4tdstUvFw_pyQ");
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		@SuppressWarnings("unchecked")
		Map<String, Object> value = (LinkedHashMap<String, Object>)claims.getBody().get("access-Token");
		System.out.println("dkdkdkdkd"+value);
		return value;
	}
}