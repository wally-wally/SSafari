package com.ssafy.edu.vue.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.ssafy.edu.vue.dto.Member;
import com.ssafy.edu.vue.dto.Message;
import com.ssafy.edu.vue.help.BoolResult;
import com.ssafy.edu.vue.service.IMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/humans/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "A5 Resouces Management 2020")
public class MessageController {

	public static final Logger logger = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	private IMessageService messageservice;
	
	@ApiOperation(value = "내 받은 메세지 전체 보기", response = List.class)
	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	public ResponseEntity<List<Message>> getMessages(HttpServletRequest rs) throws Exception {
		logger.info("1-------------getMessages-----------------------------" + new Date());
		int memberid = 0;
		if(rs.getAttribute("loginMember")!=null) {
			Member member = (Member) rs.getAttribute("loginMember");
			memberid = member.getMemberid();
		}
		List<Message> messages = messageservice.getMessages(memberid);

		return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
	}
	
	@ApiOperation(value = "메세지 상세 보기", response = List.class)
	@RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
	public ResponseEntity<Message> getMessage(@PathVariable int id,HttpServletRequest rs) throws Exception {
		logger.info("1-------------getMessage-----------------------------" + new Date());
		int memberid = 0;
		if(rs.getAttribute("loginMember")!=null) {
			Member member = (Member) rs.getAttribute("loginMember");
			memberid = member.getMemberid();
		}
		Message message = messageservice.getMessage(id);

		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	@ApiOperation(value = "message 추가", response = List.class)
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public ResponseEntity<BoolResult> addMessage(@RequestBody Message message) throws Exception {
		logger.info("1-------------addMessage-----------------------------" + new Date());
		messageservice.addMessage(message);
		BoolResult nr=new BoolResult();
   		nr.setName("addMessage");
   		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
}
