package com.biz.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value="/user")
@Controller

public class UserController {
	
	@RequestMapping(value= {"/",""},method=RequestMethod.GET)
	public String user() {
		return "home";
	}

}
