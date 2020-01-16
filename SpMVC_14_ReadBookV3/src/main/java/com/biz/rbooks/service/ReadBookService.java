package com.biz.rbooks.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.rbooks.domain.ReadBookVO;
import com.biz.rbooks.repository.ReadBookDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // private final 필드에 대한 생성자를 자동으로 만들고 autowired를 붙여 세팅해주는 lombok annotation
@Service
public class ReadBookService {
	private final ReadBookDao rBookDao;

	public int insert(ReadBookVO rBookVO) {
		// TODO Auto-generated method stub
		int ret = rBookDao.insert(rBookVO);
		return ret;
	}

	public List<ReadBookVO> selectAll() {
		// TODO Auto-generated method stub
		return rBookDao.selectAll();
	}

	public ReadBookVO findBySeq(long rb_seq) {
		// TODO Auto-generated method stub
		return rBookDao.findBySeq(rb_seq);
	}
}
