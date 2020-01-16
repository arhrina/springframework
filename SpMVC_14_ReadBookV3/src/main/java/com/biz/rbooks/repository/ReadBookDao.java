package com.biz.rbooks.repository;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.biz.rbooks.domain.ReadBookVO;

public interface ReadBookDao {
	
	public ReadBookVO findBySeq(long rbSeq);
	
	public List<ReadBookVO> findByBCode(String rbCode); // 도서코드로 조회
	
	public int insert(ReadBookVO rBookVO);

	public List<ReadBookVO> selectAll();
}
