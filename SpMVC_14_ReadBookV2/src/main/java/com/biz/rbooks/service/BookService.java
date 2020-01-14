package com.biz.rbooks.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.biz.rbooks.domain.BookVO;
import com.biz.rbooks.repository.BookDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // private final로 필드만 만들어주면 된다. 생성자를 만들어 inject(autowired)를 묶어줘야하는데 대신 해준다
@Service
public class BookService {
	private final BookDao bDao;
	
	public List<BookVO> selectAll(){
		return bDao.selectAll();
	}

	public int insert(BookVO bookVO) {
		// TODO Auto-generated method stub
		Random rnd = new Random();
		String b_code = rnd.nextGaussian() + ""; 
		bookVO.setB_code(b_code);
		bookVO.setB_name("test");
		bookVO.setB_auther("홍길동");
		return bDao.insert(bookVO);
	}
}
