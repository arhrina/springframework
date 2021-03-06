package com.biz.bbs.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.biz.bbs.domain.CommentVO;

public interface CommentDao {
	@Select("SELECT * FROM tbl_comment")
	public List<CommentVO> selectAll();
	public CommentVO findById(long c_id);
	
	@Select("SELECT * FROM tbl_comment WHERE c_b_id = #{c_b_id} ORDER BY c_date_time DESC")
	public List<CommentVO> findByBId(long c_b_id); // 원글에 달린 코멘트들 가져오기
	@Select("SELECT * FROM tbl_comment WHERE c_p_id = #{c_p_id}")
	public List<CommentVO> findByPId(long c_p_id);
	
	public int insert(CommentVO cVO);
	public int update(CommentVO cVO);
	
	@Delete("DELETE FROM tbl_comment WHERE c_id = #{c_id}")
	public int delete(long c_id);
}
