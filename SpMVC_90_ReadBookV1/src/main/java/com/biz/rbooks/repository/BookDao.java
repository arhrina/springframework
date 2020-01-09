package com.biz.rbooks.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.biz.rbooks.domain.BookVO;

@Mapper
public interface BookDao {
	@Select("SELECT * FROM tbl_books")
	List<BookVO> selectAll(); // 서비스에서 사용할, 모든 도서정보 조회
	
	@InsertProvider(type=BookSQL.class, method="insert_sql")
	int insert(BookVO bVO); // 서비스에서 사용할, 도서정보 DB에 입력
	
	@UpdateProvider(type=BookSQL.class, method="update_sql")
	int update(BookVO bVO); // 서비스에서 사용할, 도서정보 수정
	
	@Select("SELECT * FROM tbl_books WHERE b_name = #{b_name}")
	BookVO findByName(String b_name); // 서비스에서, 도서정보를 수정, 삭제 할 때 사용할 도서이름으로 자료가져오기
	
	@Delete("DELETE * FROM tbl_books WHERE b_code = #{b_code}")
	int delete(String b_code); // b_code값으로 자료 삭제
}
