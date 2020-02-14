package com.ssafy.edu.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.edu.vue.dao.MemberDaoImpl;
import com.ssafy.edu.vue.dto.AuthRequest;
import com.ssafy.edu.vue.dto.LikePost;
import com.ssafy.edu.vue.dto.Member;


@Service
public class MemberServiceImpl implements IMemberService {
	
	@Autowired
	private MemberDaoImpl memberdao;

	@Override
	@Transactional(readOnly=true)
	public List<Member> getMemberList() {
		return memberdao.getMemberList();
	}

	@Override
	@Transactional(readOnly=true)
	public Member getMember(int memberid) {
		return memberdao.getMember(memberid);
	}

	@Override
	@Transactional
	public void addMember(Member member) {
		memberdao.addMember(member);
	}
	
	@Override
	@Transactional
	public void updateMember(Member member) {
		memberdao.updateMember(member);
	}

	@Override
	@Transactional
	public void deleteMember(int memberid) {
		memberdao.deleteMember(memberid);
	}

	@Override
	@Transactional(readOnly=true)
	public Member checkLogin(Member member) {
		return memberdao.checkLogin(member);
	}

	@Override
	@Transactional(readOnly=true)
	public int checkEmail(String email) {
		return memberdao.checkEmail(email);
	}

	@Override
	@Transactional(readOnly=true)
	public int checkUsername(String username) {
		return memberdao.checkUsername(username);
	}

	@Override
	@Transactional(readOnly=true)
	public int checkUsers() {
		return memberdao.checkUsers();
	}

	@Override
	@Transactional
	public void updateMemberAuth(Member member) {
		memberdao.updateMemberAuth(member);
	}

	@Override
	@Transactional(readOnly=true)
	public int checkDelflag(String email) {
		return memberdao.checkDelflag(email);
	}

	@Override
	@Transactional
	public void authRequest(AuthRequest ar) {
		memberdao.authRequest(ar);
	}

	@Override
	@Transactional
	public void updatePassword(Member member) {
		memberdao.updatePassword(member);
	}

	@Override
	@Transactional
	public void deleteAuthRequest(int memberid) {
		memberdao.deleteAuthRequest(memberid);
	}

	@Override
	@Transactional(readOnly=true)
	public List<AuthRequest> getAuthRequestList() {
		return memberdao.getAuthRequestList();
	}

	@Override
	@Transactional(readOnly=true)
	public AuthRequest getAuthRequest(int memberid) {
		return memberdao.getAuthRequest(memberid);
	}

	@Override
	@Transactional
	public void updateLocationUnit(AuthRequest ar) {
		memberdao.updateLocationUnit(ar);
	}

	@Override
	@Transactional(readOnly=true)
	public List<LikePost> getMemberLikePost(int memberid) {
		return memberdao.getMemberLikePost(memberid);
	}

}
