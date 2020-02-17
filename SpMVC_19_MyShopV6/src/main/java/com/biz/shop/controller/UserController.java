package com.biz.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value="/user")
@Controller
public class UserController {

	// 관리자가 아닌 사용자의 화면을 보여줄 컨트롤러
	@RequestMapping(value={"", "/"}, method=RequestMethod.GET)
	public String user() {
		return "home";
	}
}
