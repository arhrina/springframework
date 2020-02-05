package com.biz.bbs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.bbs.domain.CommentVO;
import com.biz.bbs.mapper.CommentDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	private final CommentDao cmmDao;

	public int insert(CommentVO cmmVO) {
		// TODO Auto-generated method stub
		return cmmDao.insert(cmmVO);
	}

	public List<CommentVO> selectAll(long cmt_p_id) {
		// TODO Auto-generated method stub
		List<CommentVO> cmmList = cmmDao.selectAll(cmt_p_id);
		return cmmList;
	}
}
