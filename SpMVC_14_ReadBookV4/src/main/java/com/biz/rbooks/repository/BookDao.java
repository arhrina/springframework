package com.biz.rbooks.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.biz.rbooks.domain.BookVO;

public interface BookDao {
	@Select("SELECT * FROM tbl_books")
	public List<BookVO> selectAll();
	
	public int insert(BookVO bookVO);
	
	/*
	 * dao에 있는 method에 annotation을 붙이지 않으면 mapper.xml에서 id를 검색(메서드와 이름이 같은)하여 해당 query를 사용
	 */
	public List<BookVO> findByBNames(@Param("name") List<String> names); // 여러개의 값을 보내려면 param이 붙어야한다

	@Select("SELECT * FROM tbl_books WHERE b_code = #{bCode}")
	public BookVO findByBCode(String bCode);
}
