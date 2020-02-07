package com.biz.crawl.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.biz.crawl.domain.NaverMovieVO;

public interface NaverDao {
	
	@Select("SELECT * FROM tbl_movie")
	public List<NaverMovieVO> selectAll();
	
	public int insert(NaverMovieVO mVO);
	public int insertAll(List<NaverMovieVO> mList); // 변수 이름은 상관없고, 리스트를 넘겨준다
	
	@Delete("DELETE FROM tbl_movie")
	public void deleteAll(); // 전체 지우기
}
