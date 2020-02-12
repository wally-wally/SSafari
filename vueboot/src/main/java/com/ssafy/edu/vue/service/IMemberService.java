package com.ssafy.edu.vue.service;

import java.util.List;

import com.ssafy.edu.vue.dto.AuthRequest;
import com.ssafy.edu.vue.dto.Member;

public interface IMemberService {

	List<Member> getMemberList();

	Member getMember(int memberid);

	void addMember(Member member);

	void updateMember(Member member);

	void deleteMember(int memberid);

	Member checkLogin(Member member);

	int checkEmail(String email);

	int checkUsername(String username);

	int checkUsers();

	void updateMemberAuth(Member member);

	int checkDelflag(String email);

	void authRequest(AuthRequest ar);

	void updatePassword(Member member);

	void deleteAuthRequest(int memberid);

	List<AuthRequest> getAuthRequestList();

	AuthRequest getAuthRequest(int memberid);

	void updateLocationUnit(AuthRequest ar);

}
