package com.biz.ems.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.ems.domain.EmailVO;

@SessionAttributes("emailVO") // emailVO에 값을 자동으로 넣어주게. 사용하려면 반드시 emailVO를 만들어줘야한다. 생성자 사용
@Controller
@RequestMapping(value="/ems")
public class EMSController {
	
	
	/*
	 * ModelAttribute 생성자 메서드. Controller에 ModelAttribute 객체가 없거나 null인 상태이면 이 메서드를 실행하여 emailVO를 생성
	 * 하지만 한번이라도 생성자 메서드가 호출되어 생성된 상태라면 다시 동작하지 않는다
	 * 해당 페이지에서 새로고침을 해도 처음 진입할 때 들어가있던 값에서 변경되지 않는다. 관리를 해주어야한다
	 */
	
	
	@ModelAttribute("emailVO")
	public EmailVO makeEmailVO() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat st = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date(); // java util date
		String curDate = sd.format(date);
		String curTime = st.format(date);
		
		EmailVO emailVO = EmailVO.builder()
				.send_date(curDate)
				.send_time(curTime)
				.build();
		// 지금 날짜랑 시간을 넣고 그 값을 담는다
		
		return emailVO;
		
	}

	@RequestMapping(value="/input", method=RequestMethod.GET)
	public String input(@ModelAttribute("emailVO") EmailVO emailVO, Model model, SessionStatus status) {
		// emailVO = makeEmailVO(); // 새로고침을 할 때마다 새로이 생성자를 시행해주거나
		status.setComplete(); // 세션을 초기화해주는 방법이 있다
		
		model.addAttribute("emailVO", emailVO);
		model.addAttribute("BODY", "WRITE");
		return "home";
	}
}
