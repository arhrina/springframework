package com.biz.iolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value="/parameter")
@Controller
public class ParamController {

	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view() {
		// return "/param/view"; // null은 이것과 같음
		return null;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET) // 주소창에서 URL을 입력해서
	// enter를 쳤을 때 받는 것이 GET
	public String update(String id) { // parameter로 수신되는 값은 기본적으로 문자열이므로 int로 바꾸는 과정에서 오류 발생
		// 숫자를 받으려고 하면 매개변수는 String으로 받고 이 메소드 내부에서 IntegervalueOf를 사용
		return null;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(String code, Model model) {
		System.out.println(code); // action에서 지정된 code와 input name이 같으므로 두개값 모두 전송되므로
		// 쓰지않는 값을 URL에 지정해서 넘겨야한다
		return null;
	}
}
