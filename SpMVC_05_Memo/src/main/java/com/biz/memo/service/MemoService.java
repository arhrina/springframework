package com.biz.memo.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.memo.domain.MemoDTO;
import com.biz.memo.persistence.MemoDao;

// @Repository Service 대신 사용할 수 있다
@Service
public class MemoService {
	
	@Autowired
	SqlSession sqlSession;
	
	MemoDao mDao;
	
	/*
	 * service를 사용하려고 하면 생성자처럼 자동으로 sqlSession으로부터 MemoDao mapper를 받아 mDao를 사용할 수 있게 초기화
	 */
	@Autowired
	public void getMapper() {
		mDao = sqlSession.getMapper(MemoDao.class);
	}

	public List<MemoDTO> getAllList() {
		// TODO 전체 메모리스트를 DB로부터 가져와서 Controller로 리턴
		return mDao.selectAll();
	}

	public List<MemoDTO> getSearchList(String m_subject) {
		// TODO 제목으로 검색하기
		MemoDTO memoDTO = MemoDTO.builder().m_subject(m_subject).build();
		return mDao.findBySearch(memoDTO);
	}

	public int insert(MemoDTO memoDTO) {
		// TODO Auto-generated method stub
		return mDao.insert(memoDTO);
	}
}
