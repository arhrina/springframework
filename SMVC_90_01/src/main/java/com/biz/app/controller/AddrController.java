package com.biz.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.app.AddrVO;
import com.biz.app.service.AddrService;

// VO만들고 Service만들고 Controller 만들기

@RequestMapping(value="/addr")
@Controller
public class AddrController {
	
	@Autowired
	AddrService aService;

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		
		List<AddrVO> addrList = aService.addrListAll();
		
		model.addAttribute("ADDR_LIST", addrList); // 대문자로 쓰는 이유는 선생님 버릇
		
		return "addr";
		// view에 JSP파일 만들기
	}
}
