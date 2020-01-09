package com.biz.rbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.rbooks.domain.BookVO;
import com.biz.rbooks.repository.BookDao;

@Service
public class BookService {
	BookDao bDao;
	
	
	@Autowired
	public BookService(BookDao bDao) {
		super();
		this.bDao = bDao;
	}

	// 컨트롤러에서 사용할, VO를 매개변수로 사용하여 Create(insert) 할 메서드 
	public int insertBook(BookVO bVO) {
		return bDao.insert(bVO);
	}
	
	// 컨트롤러로 보낼 모든 도서에 대한 정보 조회 Read(select)
	public List<BookVO> selectAll() {
		return bDao.selectAll();
	}
	
	// view에서 이름으로 검색해서 얻어온 자료로 update 폼으로 연결하고 Update 수행
	public int updateBook(BookVO bVO) {
		bVO = bDao.findByName(bVO.getB_name());
		return bDao.update(bVO);
	}
	
	// view에서 이름으로 검색해서 얻어온 자료로 confirm을 확인하고 Delete
	public int deleteBook(String b_code) {
		return bDao.delete(b_code);
	}
}
