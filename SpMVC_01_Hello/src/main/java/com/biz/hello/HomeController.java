package com.biz.hello;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value="my", method = RequestMethod.POST) // method가 안붙어있으면 get과 post 둘 다 받음
	public String my(Model model, String strName, String strDept) {
		// 매개변수로 지정해주면 스프링 컨테이너가 처리해준다
		System.out.println(strName);
		System.out.println(strDept);
		return "mypage";
	}
	/*
	 * URI에 주소를 지정하면 GET 명령이다
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.POST) // 서블릿의 doPost
	public String home(Model model) {
		return "home";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET) // 서블릿의 doGet이 하는일과 같다
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("st_name", "이몽룡이");
		model.addAttribute("st_dept", "컴공");
		model.addAttribute("st_grade", 3);
		
		return "home";
	}
	
}
