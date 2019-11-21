package com.biz.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value="/member") // tomcat에서 가장 위의 value를 찾고 여기에 메소드들의 value를 추가한다
// tomcat 3.1 이하에선 Controller에 RequestMapping이 붙지 않는다
@Controller
public class MemberController {

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("BODY", "LOGIN");
		return "home";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(Model model) {
		model.addAttribute("BODY", "JOIN");
		return "home";
	}
}
