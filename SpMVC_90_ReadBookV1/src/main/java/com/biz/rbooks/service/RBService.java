package com.biz.rbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.rbooks.domain.RBVO;
import com.biz.rbooks.repository.RBDao;

@Service
public class RBService {
	RBDao rbDao;

	@Autowired
	public RBService(RBDao rbDao) {
		super();
		this.rbDao = rbDao;
	}

	public List<RBVO> findByBCode(String b_code) {
		// TODO Auto-generated method stub
		return rbDao.findByBCode(b_code);
	}

	public int insert(RBVO rbVO) {
		// TODO Auto-generated method stub
		return rbDao.insert(rbVO);
	}

	public RBVO findBySEQ(String seq) {
		// TODO Auto-generated method stub
		int iSeq = Integer.valueOf(seq);
		return rbDao.findBySEQ(iSeq);
	}

	public int delete(RBVO rbVO) {
		// TODO Auto-generated method stub
		return rbDao.delete(rbVO.getRb_seq());
	}
}
