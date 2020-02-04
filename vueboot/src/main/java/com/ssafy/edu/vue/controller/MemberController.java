package com.ssafy.edu.vue.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.edu.vue.dto.CheckSignUp;
import com.ssafy.edu.vue.dto.Member;
import com.ssafy.edu.vue.dto.Portfolio;
import com.ssafy.edu.vue.help.BoolResult;
import com.ssafy.edu.vue.service.IMemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/humans/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "A5 Resouces Management 2020")
public class MemberController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private IMemberService memberservice;
	
	@ApiOperation(value = "member 전체 목록 보기", response = List.class)
	@RequestMapping(value = "/memberList", method = RequestMethod.GET)
	public ResponseEntity<List<Member>> getMemberList() throws Exception {
		logger.info("1-------------getMemberList-----------------------------" + new Date());
		List<Member> members = memberservice.getMemberList();
		if (members == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
	}
	
	@ApiOperation(value = "member 내 정보", response = List.class)
	@RequestMapping(value = "/member/{memberid}", method = RequestMethod.GET)
	public ResponseEntity<Member> getMember(@PathVariable int memberid) throws Exception {
		logger.info("1-------------getMember-----------------------------" + new Date());
		Member member = memberservice.getMember(memberid);
		if (member == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	
	@ApiOperation(value = "member 로그인", response = List.class)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Member> login(@RequestBody Member member) throws Exception {
		logger.info("1-------------login-----------------------------" + new Date());	
		Member login=memberservice.checkLogin(member);
		return new ResponseEntity<Member>(login, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "member 회원가입 (eamil or username 중복 시 false)", response = List.class)
	@RequestMapping(value = "/member", method = RequestMethod.POST)
	public ResponseEntity<CheckSignUp> addMember(@RequestBody Member member) throws Exception {
		logger.info("1-------------addMember-----------------------------" + new Date());
		int email = memberservice.checkEmail(member.getEmail());
		int username = memberservice.checkUsername(member.getUsername());
		CheckSignUp result = new CheckSignUp();
		if(email==0 && username==0) {
			result.setSignup(true);
			result.setMessage("사용 가능");
			memberservice.addMember(member);
		}else if(email>=1 && username==0) {
			result.setSignup(false);
			result.setMessage("email 중복");
		}else if(email==0 && username>=1) {
			result.setSignup(false);
			result.setMessage("username 중복");
		}else {
			result.setSignup(false);
			result.setMessage("email, username 중복");
		}
		return new ResponseEntity<CheckSignUp>(result, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "member 회원정보 수정", response = BoolResult.class)
	@RequestMapping(value = "/member", method = RequestMethod.PUT)
	public ResponseEntity<BoolResult> updateMember(@RequestBody Member member) throws Exception {
		logger.info("1-------------updateMember-----------------------------" + new Date());
		memberservice.updateMember(member);
		BoolResult nr=new BoolResult();
   		nr.setName("updateMember");
   		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "member 회원 탈퇴", response = BoolResult.class)
	@RequestMapping(value = "/member/{memberid}", method = RequestMethod.PUT)
	public ResponseEntity<BoolResult> deleteMember(@PathVariable int memberid) throws Exception {
		logger.info("1-------------deleteMember-----------------------------" + new Date());
		memberservice.deleteMember(memberid);
		BoolResult nr=new BoolResult();
   		nr.setName("deleteMember");
   		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "가입 회원 수 출력", response = BoolResult.class)
	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public ResponseEntity<BoolResult> checkUsers() throws Exception {
		logger.info("1-------------checkUsers-----------------------------" + new Date());
		int email = memberservice.checkUsers();
		BoolResult nr=new BoolResult();
   		nr.setName("checkUsers");
   		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "member 회원 권한 수정", response = BoolResult.class)
	@RequestMapping(value = "/memberAuth", method = RequestMethod.PUT)
	public ResponseEntity<BoolResult> updateMemberAuth(@RequestBody Member member) throws Exception {
		logger.info("1-------------updateMemberAuth-----------------------------" + new Date());
		memberservice.updateMemberAuth(member);
		BoolResult nr=new BoolResult();
   		nr.setName("updateMemberAuth");
   		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
}
