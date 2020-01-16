package com.biz.rbooks.service;

import java.util.Arrays;
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

	public List<BookVO> findByBNames(String text) {
		// TODO Auto-generated method stub
		List<String> names = Arrays.asList(text.split(" "));
		// text를 빈칸으로 분해해서 문자열배열로 만들고 배열을 문자열 리스트로 형변환해서 names에 값 담기
		List<BookVO> bookList = bDao.findByBNames(names); // BookVO 리스트에 분해한 문자열 리스트로 bDao에서 findByBNames한 자료값을 담기
		return bookList;
	}

	public BookVO findByBCode(String bCode) {
		// TODO Auto-generated method stub
		BookVO bookVO = bDao.findByBCode(bCode);
		return bookVO;
	}
}
