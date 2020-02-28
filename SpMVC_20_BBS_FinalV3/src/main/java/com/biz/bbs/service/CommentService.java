package com.biz.bbs.service;

import java.util.List;

import com.biz.bbs.domain.CommentVO;

public interface CommentService {
	public List<CommentVO> selectAll();
	public CommentVO findById(long c_id);
	public List<CommentVO> findByBId(long c_b_id); // 원글에 달린 코멘트들 가져오기
	public List<CommentVO> findByPId(long c_p_id);
	public int insert(CommentVO cVO);
	public int update(CommentVO cVO);
	public int delete(long c_id);
}
