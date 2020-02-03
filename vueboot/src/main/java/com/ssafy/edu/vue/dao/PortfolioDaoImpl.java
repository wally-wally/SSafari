package com.ssafy.edu.vue.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.edu.vue.dto.Commentportfolio;
import com.ssafy.edu.vue.dto.Portfolio;
import com.ssafy.edu.vue.dto.Sugang;

@Repository
public class PortfolioDaoImpl {

	String ns = "ssafy.portfolio.";
	@Autowired
	private SqlSession sqlSession;
	
	public List<Portfolio> getPortfolioList(int memberid) {
		return sqlSession.selectList(ns+"getPortfolioList",memberid);
	}

	public Portfolio getPortfolio(int portfolioid) {
		return sqlSession.selectOne(ns+"getPortfolio", portfolioid);
	}

	public void addPortfolio(Portfolio portfolio) {
		sqlSession.insert(ns+"addPortfolio",portfolio);
	}

	public void updatePortfolio(Portfolio portfolio) {
		sqlSession.update(ns+"updatePortfolio",portfolio);
	}

	public void deletePortfolio(int portfolioid) {
		sqlSession.delete(ns+"deletePortfolio",portfolioid);
	}

	public List<Portfolio> getPortfolios() {
		return sqlSession.selectList(ns+"getPortfolios");
	}

	public List<Commentportfolio> getCommentPortfolio(int portfolioid) {
		return sqlSession.selectList(ns+"getCommentPortfolio",portfolioid);
	}

	public void addCommentPortfolio(Commentportfolio commentportfolio) {
		sqlSession.insert(ns+"addCommentPortfolio",commentportfolio);
	}

	public void updateCommentPortfolio(Commentportfolio commentportfolio) {
		sqlSession.update(ns+"updateCommentPortfolio",commentportfolio);
	}

	public void deleteCommentPortfolio(int cportfolioid) {
		sqlSession.delete(ns+"deleteCommentPortfolio",cportfolioid);
	}

	public int getCommentPortfolioCount(int portfolioid) {
		return sqlSession.selectOne(ns+"getCommentPortfolioCount",portfolioid);
	}

	public void registerSugang(Sugang sugang) {
		sqlSession.insert(ns+"registerSugang",sugang);
	}

	public void deleteSugang(Sugang sugang) {
		sqlSession.delete(ns+"deleteSugang",sugang);
	}
	
	public int isApplicant(Sugang sugang) {
		return sqlSession.selectOne(ns + "isApplicant", sugang);
	}
	
	public List<Sugang> getSugangMember(int portfolioid) {
		return sqlSession.selectList(ns+"getSugangMember",portfolioid);
	}
	
	public void addSugangMember(int portfolioid) {
		sqlSession.update(ns+"addSugangMember",portfolioid);
	}
	
	public void subSugangMember(int portfolioid) {
		sqlSession.update(ns+"subSugangMember",portfolioid);
	}

	public List<Portfolio> getMemberPortfolio(int memberid) {
		return sqlSession.selectList(ns+"getMemberPortfolio",memberid);
	}
}
