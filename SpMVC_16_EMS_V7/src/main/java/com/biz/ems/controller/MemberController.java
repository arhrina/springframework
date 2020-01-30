package com.biz.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.ems.domain.NaverMember;
import com.biz.ems.domain.NaverReturnAuth;
import com.biz.ems.domain.NaverTokenVO;
import com.biz.ems.service.NaverLoginService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping(value="/member")
public class MemberController {

	private final NaverLoginService nLoginService;
	
	@RequestMapping(value="/naver",method=RequestMethod.GET)
	public String naver_login() {
		
		String apiURL = nLoginService.oAuthLoginGet();
		return "redirect:" + apiURL; // service에서 받은 문자열을 가지고 naver에 request
	
	}
	/*
	 * 네이버에 로그인 요청을 보낼 때
	 * 네이버가 리턴할 URL 부분
	 * 외부에서 접속할 수 있는 URL이어야한다(포트포워딩이 되어있어야)
	 * 
	 * 네이버에 로그인이 성공하면 네이버에서 로그인 인증정보를 보내준다
	 */
	@ResponseBody
	@RequestMapping(value="/naver/ok", method=RequestMethod.GET) // callback하는 데이터를 받아올 URL
	public NaverMember naver_ok(@ModelAttribute NaverReturnAuth naverOK) { // 로그인했을 때 VO에 실려온다
		NaverTokenVO nToken = nLoginService.oAuthAccessGetToken(naverOK);
		NaverMember memberVO = nLoginService.getNaverMemberInfo(nToken);
		return memberVO;
	}
}
