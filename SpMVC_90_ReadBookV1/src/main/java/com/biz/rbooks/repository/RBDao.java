package com.biz.rbooks.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.biz.rbooks.domain.RBVO;

public interface RBDao {

	@Select("SELECT * FROM tbl_read_book WHERE rb_bcode = #{b_code}")
	List<RBVO> findByBCode(String b_code);

	@Insert("INSERT INTO tbl_read_book(rb_seq, rb_bcode, rb_date, rb_stime, rb_rtime, rb_subject, rb_text, rb_star) VALUES(SEQ_READ_BOOK.NEXTVAL, #{rb_bcode, jdbcType=VARCHAR}, #{rb_date, jdbcType=VARCHAR},#{rb_stime, jdbcType=VARCHAR},#{rb_rtime, jdbcType=VARCHAR},#{rb_subject, jdbcType=VARCHAR},#{rb_text, jdbcType=VARCHAR},#{rb_star, jdbcType=VARCHAR})")
	int insert(RBVO rbVO);

	@Select("SELECT * FROM tbl_read_book WHERE rb_seq = #{iSeq}")
	RBVO findBySEQ(int iSeq);

	@Delete("DELETE FROM tbl_read_book WHERE rb_seq = #{l}")
	int delete(long l);
}
