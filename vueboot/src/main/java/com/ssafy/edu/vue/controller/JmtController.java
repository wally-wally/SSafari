package com.ssafy.edu.vue.controller;

import java.util.Date;
import java.util.List;

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

import com.ssafy.edu.vue.dto.Jmt;
import com.ssafy.edu.vue.dto.Member;
import com.ssafy.edu.vue.help.BoolResult;
import com.ssafy.edu.vue.service.IJmtService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/humans/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "A5 Resouces Management 2020")
public class JmtController {

	public static final Logger logger = LoggerFactory.getLogger(JmtController.class);

	@Autowired
	private IJmtService jmtservice;
	
	@ApiOperation(value = "jmt 전체 보기", response = List.class)
	@RequestMapping(value = "/jmts", method = RequestMethod.GET)
	public ResponseEntity<List<Jmt>> getJmts(HttpServletRequest rs) throws Exception {
		logger.info("1-------------getJmts-----------------------------" + new Date());
		int memberid = 0;
		if(rs.getAttribute("loginMember")!=null) {
			Member member = (Member) rs.getAttribute("loginMember");
			memberid = member.getMemberid();
		}
		List<Jmt> jmts = jmtservice.getJmts(memberid);
		if (jmts == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Jmt>>(jmts, HttpStatus.OK);
	}
	
	@ApiOperation(value = "jmt 상세 보기", response = List.class)
	@RequestMapping(value = "/jmt/{id}", method = RequestMethod.GET)
	public ResponseEntity<Jmt> getJmt(@PathVariable int id,HttpServletRequest rs) throws Exception {
		logger.info("1-------------getJmt-----------------------------" + new Date());
		Jmt jmt = jmtservice.getJmt(id);
		if (jmt == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Jmt>(jmt, HttpStatus.OK);
	}
	
	@ApiOperation(value = "jmt 추가", response = List.class)
	@RequestMapping(value = "/jmt", method = RequestMethod.POST)
	public ResponseEntity<BoolResult> addJmt(@RequestBody Jmt jmt) throws Exception {
		logger.info("1-------------addJmt-----------------------------" + new Date());
		jmtservice.addJmt(jmt);
		BoolResult nr=new BoolResult();
   		nr.setName("addJmt");
   		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "jmt 수정", response = BoolResult.class)
	@RequestMapping(value = "/jmt", method = RequestMethod.PUT)
	public ResponseEntity<BoolResult> updateJmt(@RequestBody Jmt jmt) throws Exception {
		logger.info("1-------------updateJmt-----------------------------" + new Date());
		jmtservice.updateJmt(jmt);
		BoolResult nr=new BoolResult();
   		nr.setName("updateJmt");
   		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "jmt 삭제", response = BoolResult.class)
	@RequestMapping(value = "/jmt/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<BoolResult> deleteJmt(@PathVariable int id) throws Exception {
		logger.info("1-------------deleteJmt-----------------------------" + new Date());
		jmtservice.deleteJmt(id);
		BoolResult nr=new BoolResult();
   		nr.setName("deleteJmt");
   		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
}
