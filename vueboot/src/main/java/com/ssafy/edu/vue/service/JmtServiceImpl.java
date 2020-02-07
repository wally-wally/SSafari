package com.ssafy.edu.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.edu.vue.dao.CodeDaoImpl;
import com.ssafy.edu.vue.dao.JmtDaoImpl;
import com.ssafy.edu.vue.dto.Code;
import com.ssafy.edu.vue.dto.Jmt;


@Service
public class JmtServiceImpl implements IJmtService {
	
	@Autowired
	private JmtDaoImpl jmtdao;

	@Override
	@Transactional(readOnly=true)
	public List<Jmt> getJmts(int memberid) {
		return jmtdao.getJmts(memberid);
	}

	@Override
	@Transactional(readOnly=true)
	public Jmt getJmt(Jmt jmt) {
		return jmtdao.getJmt(jmt);
	}

	@Override
	@Transactional
	public void addJmt(Jmt jmt) {
		jmtdao.addJmt(jmt);
	}

	@Override
	@Transactional
	public void updateJmt(Jmt jmt) {
		jmtdao.updateJmt(jmt);
	}

	@Override
	@Transactional
	public void deleteJmt(int id) {
		jmtdao.deleteJmt(id);
	}

}
