package com.biz.gallery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.gallery.domain.MemberVO;
import com.biz.gallery.repository.MemberDao;

@Service
public class MemberService {
	private final MemberDao memberDao; // final을 붙이면 고정된다. 값을 넣어줘야 final의 에러가 사라지니 생성자로 넣어준다
	private final BCryptPasswordEncoder passwordEncoder;
	
	
	@Autowired
	public MemberService(MemberDao memberDao, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.memberDao = memberDao;
		this.passwordEncoder = passwordEncoder;
	}

	public int insert(MemberVO memberVO) {
		// 입력된 회원정보 중에 password를 암호화
		String cryptText = passwordEncoder.encode(memberVO.getU_password());
		int nCount = memberDao.memberCount();
		if(nCount < 1) { // 가입된 사람의 숫자로 가입하는 사람의 등급을 조정
			memberVO.setU_grade("ADMIN");
		}
		else if(nCount < 4) {
			memberVO.setU_grade("MEM");
		}
		else {
			memberVO.setU_grade("GUEST");
		}
		memberVO.setU_name(memberVO.getU_id()); // 테스트를 위해 임시로 이름을 id로 대체
		return memberDao.insert(memberVO);
	}

	public MemberVO loginCheck(MemberVO memberVO) {
		// TODO Auto-generated method stub
		String u_id = memberVO.getU_id();
		String u_password = memberVO.getU_password();
		
		MemberVO loginMemberVO = memberDao.findById(u_id);
		if(loginMemberVO != null) {
			String cryptPassword = passwordEncoder.encode(u_password);
			if(passwordEncoder.matches(u_password, cryptPassword)) {
				return loginMemberVO;
			}
		}
		return null;
	}
}
