package com.biz.rbooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.rbooks.domain.MemberVO;
import com.biz.rbooks.repository.MemberDao;

@Service
public class MemberService {

	private final MemberDao mDao;
	private final BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public MemberService(MemberDao mDao, BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		this.mDao = mDao;
	}
	
	public int joinUser(MemberVO mVO) {
		String encrypPass = passwordEncoder.encode(mVO.getM_password()); // 패스워드 암호화
		mVO.setM_password(encrypPass); // 암호화된 패스워드로 세팅
		return mDao.insert(mVO);
	}
}
