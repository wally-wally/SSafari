package com.ssafy.edu.vue.service;

import java.util.List;

import com.ssafy.edu.vue.dto.Jmt;

public interface IJmtService {

	public List<Jmt> getJmts(int memberid);

	public Jmt getJmt(int id);

	public void addJmt(Jmt jmt);

	public void updateJmt(Jmt jmt);

	public void deleteJmt(int id);

}
