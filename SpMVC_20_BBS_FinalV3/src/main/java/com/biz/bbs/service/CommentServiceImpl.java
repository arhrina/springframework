package com.biz.bbs.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.bbs.domain.CommentVO;
import com.biz.bbs.repository.CommentDao;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentDao cDao;

	@Override
	public List<CommentVO> selectAll() {
		// TODO Auto-generated method stub
		return cDao.selectAll();
	}

	@Override
	public CommentVO findById(long c_id) {
		// TODO Auto-generated method stub
		CommentVO cmtVO = cDao.findById(c_id);
		return cmtVO;
	}

	@Override
	public List<CommentVO> findByPId(long c_p_id) {
		// TODO Auto-generated method stub
		List<CommentVO> cmtList = cDao.findByPId(c_p_id);
		return cmtList;
	}
	
	@Override
	public List<CommentVO> findByBId(long c_b_id) {
		// TODO Auto-generated method stub
		List<CommentVO> cmtList = cDao.findByBId(c_b_id);
		return cmtList;
	}

	@Override
	public int insert(CommentVO cVO) {
		// TODO Auto-generated method stub
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter df 
			= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		cVO.setC_date_time(ldt.format(df).toString());
		int ret = cDao.insert(cVO);
		return ret;
	}

	@Override
	public int update(CommentVO cVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long c_id) {
		// TODO Auto-generated method stub
		int ret = cDao.delete(c_id);
		return ret;
	}

}
