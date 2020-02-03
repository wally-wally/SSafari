package com.ssafy.edu.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.edu.vue.dao.PortfolioDaoImpl;
import com.ssafy.edu.vue.dto.Commentportfolio;
import com.ssafy.edu.vue.dto.Commentportfolio;
import com.ssafy.edu.vue.dto.Portfolio;
import com.ssafy.edu.vue.dto.Sugang;


@Service
public class PortfolioServiceImpl implements IPortfolioService {
	
	@Autowired
	private PortfolioDaoImpl portfoliodao;

	@Override
	@Transactional(readOnly=true)
	public List<Portfolio> getPortfolioList(int memberid) {
		return portfoliodao.getPortfolioList(memberid);
	}

	@Override
	@Transactional(readOnly=true)
	public Portfolio getPortfolio(int portfolioid) {
		return portfoliodao.getPortfolio(portfolioid);
	}

	@Override
	@Transactional
	public void addPortfolio(Portfolio portfolio) {
		portfoliodao.addPortfolio(portfolio);
	}

	@Override
	@Transactional
	public void updatePortfolio(Portfolio portfolio) {
		portfoliodao.updatePortfolio(portfolio);
	}

	@Override
	@Transactional
	public void deletePortfolio(int portfolioid) {
		portfoliodao.deletePortfolio(portfolioid);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Portfolio> getPortfolios() {
		return portfoliodao.getPortfolios();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Commentportfolio> getCommentPortfolio(int portfolioid) {
		return portfoliodao.getCommentPortfolio(portfolioid);
	}

	@Override
	@Transactional
	public void addCommentPortfolio(Commentportfolio commentportfolio) {
		portfoliodao.addCommentPortfolio(commentportfolio);
	}

	@Override
	@Transactional
	public void updateCommentPortfolio(Commentportfolio commentportfolio) {
		portfoliodao.updateCommentPortfolio(commentportfolio);
	}

	@Override
	@Transactional
	public void deleteCommentPortfolio(int cportfolioid) {
		portfoliodao.deleteCommentPortfolio(cportfolioid);
	}

	@Override
	public void registerSugang(Sugang sugang) {
		portfoliodao.registerSugang(sugang);
	}

	@Override
	public void deleteSugang(Sugang sugang) {
		portfoliodao.deleteSugang(sugang);
	}

	@Override
	public int isApplicant(Sugang sugang) {
		return portfoliodao.isApplicant(sugang);
	}
		
	@Override
	@Transactional(readOnly=true)
	public List<Sugang> getSugangMember(int portfolioid) {
		return portfoliodao.getSugangMember(portfolioid);
	}

	@Override
	public void addSugangMember(int portfolioid) {
		portfoliodao.addSugangMember(portfolioid);
	}
	
	@Override
	public void subSugangMember(int portfolioid) {
		portfoliodao.subSugangMember(portfolioid);
	}

	@Override
	public List<Portfolio> getMemberPortfolio(int memberid) {
		return portfoliodao.getMemberPortfolio(memberid);
	}

}
