package com.biz.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.ems.domain.EmailVO;
import com.biz.ems.repository.EmailDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MailService {

	private final EmailDao emsDao;
	
	public int insert(EmailVO emailVO) {
		emsDao.save(emailVO);
		return 0;
	}
	
	public int delete(long ems_seq) {
		emsDao.deleteById(ems_seq);
		return 0;
	}
	
	public List<EmailVO> selectAll() {
		List<EmailVO> emailList = (List<EmailVO>) emsDao.findAll();
		return emailList;
	}
	
	public EmailVO findBySeq(long ems_seq) {
		EmailVO emailVO = emsDao.findByEmsSeq(ems_seq);
		return emailVO;
	}
}
