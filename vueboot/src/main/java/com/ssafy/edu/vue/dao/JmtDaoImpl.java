package com.ssafy.edu.vue.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.edu.vue.dto.Jmt;

@Repository
public class JmtDaoImpl {
	
	String ns = "ssafy.jmt.";
	@Autowired
	private SqlSession sqlSession;
	
	public List<Jmt> getJmts(int memberid) {
		return sqlSession.selectList(ns+"getJmts",memberid);
	}
	public Jmt getJmt(Jmt jmt) {
		return sqlSession.selectOne(ns+"getJmt",jmt);
	}
	public void addJmt(Jmt jmt) {
		sqlSession.insert(ns+"addJmt",jmt);
	}
	public void updateJmt(Jmt jmt) {
		sqlSession.update(ns+"addJmt",jmt);
	}
	public void deleteJmt(int id) {
		sqlSession.delete(ns+"deleteJmt",id);
	}
}
