package com.biz.bbs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.bbs.domain.BBsVO;
import com.biz.bbs.mapper.BBsDao;

import lombok.RequiredArgsConstructor;

@Service("bServiceV1")
@RequiredArgsConstructor
public class BBsServiceImplV1 implements BBsService {
	
	protected final BBsDao bbsDao; // 상속받은 클래스에서만 접근가능하도록 한다.

	@Override
	public List<BBsVO> selectAll() {
		// TODO Auto-generated method stub
		return bbsDao.selectAll();
	}

	@Override
	public BBsVO findById(long bbs_id) {
		// TODO Auto-generated method stub
		return bbsDao.findById(bbs_id);
	}

	@Override
	public List<BBsVO> findBySubjects(String subject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BBsVO> findByWriters(String writer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BBsVO> findBySubAndWriter(String subject, String writer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(BBsVO bbsVO) {
		// TODO Auto-generated method stub
		long bbs_id = bbsVO.getBbs_id();
		if(bbs_id > 0) {
			bbsDao.update(bbsVO);
		}
		else {
			bbsDao.insert(bbsVO);
		}
		return 0;
	}

	@Override
	public int delete(long bbs_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BBsVO reply(BBsVO bbsVO) {
		// TODO Auto-generated method stub
		bbsVO.setBbs_p_id(bbsVO.getBbs_id()); // primary key를 p_id에 세팅하고
		bbsVO.setBbs_id(0); // primary key를 0으로 설정
		
		String subject = "re : " + bbsVO.getBbs_subject(); // 답글이라서 앞에 re를 붙인다
		bbsVO.setBbs_subject(subject);
		
		bbsDao.insert(bbsVO); // 그러고 저장하면 id값은 새로 생성되고 p_id는 원래 있던 id를 가지고 있다. p_id를 가지면 댓글로 간주
		return null;
	}

}
