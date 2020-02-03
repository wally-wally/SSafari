package com.ssafy.edu.vue.service;

import java.util.List;

import com.ssafy.edu.vue.dto.Commentportfolio;
import com.ssafy.edu.vue.dto.Portfolio;
import com.ssafy.edu.vue.dto.Sugang;


public interface IPortfolioService {
	
	List<Portfolio> getPortfolioList(int memberid);
	
	Portfolio getPortfolio(int portfolioid);
	
	void addPortfolio(Portfolio portfolio);
	
	void updatePortfolio(Portfolio portfolio);
	
	void deletePortfolio(int portfolioid);

	List<Portfolio> getPortfolios();

	List<Commentportfolio> getCommentPortfolio(int portfolioid);

	void addCommentPortfolio(Commentportfolio commentportfolio);

	void updateCommentPortfolio(Commentportfolio commentportfolio);

	void deleteCommentPortfolio(int cportfolioid);

	void registerSugang(Sugang sugang);

	void deleteSugang(Sugang sugang);

	int isApplicant(Sugang sugang);
	
	List<Sugang> getSugangMember(int portfolioid);

	void addSugangMember(int portfolioid);
	
	void subSugangMember(int portfolioid);

	List<Portfolio> getMemberPortfolio(int memberid);
}
