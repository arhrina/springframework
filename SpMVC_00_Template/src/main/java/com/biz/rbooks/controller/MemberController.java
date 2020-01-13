package com.biz.rbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.rbooks.domain.MemberVO;
import com.biz.rbooks.service.MemberService;

@RequestMapping(value="/member")
@Controller
public class MemberController {

	public final MemberService mService;

	@Autowired
	public MemberController(MemberService mService) {
		this.mService = mService;
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(MemberVO mVO) {
		mService.joinUser(mVO);
		return "redirect:/";
	}
}
