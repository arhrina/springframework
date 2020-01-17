package com.biz.rbooks.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.UpdateProvider;

import com.biz.rbooks.domain.ReadBookVO;

public interface ReadBookDao {
	
	public ReadBookVO findBySeq(long rbSeq);
	
	public List<ReadBookVO> findByBCode(String rbCode); // 도서코드로 조회
	
	public int insert(ReadBookVO rBookVO);

	public List<ReadBookVO> selectAll();

	@UpdateProvider(type=ReadBookSQL.class, method = "update_sql") // mapper가 아니라 annotation으로 쓰기
	public int update(ReadBookVO rBookVO);

	@Delete("DELETE FROM tbl_read_book WHERE rb_seq = #{rb_seq}")
	public int delete(long rb_seq);
}
