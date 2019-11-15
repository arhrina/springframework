package com.biz.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // ui는 화면의 의미
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; // bind는 연결의 의미

import com.biz.student.domain.StudentDTO;
import com.biz.student.service.StudentService;
/*
 * @Controller
 * Tomcat, Spring container에 이 클래스의 list를 추가하고 request를 대기
 */

@Controller
public class StudentController { // 컨트롤러에서 쓰는 메소드는 모두 String형
	
	@Autowired
	StudentService sService;
	/*public StudentController() {
		// TODO Auto-generated constructor stub
		sService = new StudentService();
	}*/
	@RequestMapping(value="input", method=RequestMethod.GET)
	public String input() {
		return "student/input"; 
	}
	// WEB-INF/views/student/input.jsp를 열어서 브라우저로 전송하라
	// value는 path. 겹치면 안됨. GET은 URI로 브라우저가 사용하며 POST는 form에서만 쓴다
	
	@RequestMapping(value="input", method=RequestMethod.POST)
	public String input(Model model, StudentDTO stDTO) {
		System.out.println(stDTO.toString());
		model.addAttribute("stDTO", stDTO);
		return "student/view"; // view.jsp를 열어라. 모델을 넘겨줘서 랜더링을 시작
	}
	
	@RequestMapping(value="search", method=RequestMethod.GET)
	public String search() {
		return "student/search";// return null이면 value값을 return하는것과 똑같다
	}
	
	@RequestMapping(value="view", method=RequestMethod.GET)
	public String view() {
		return "student/view";
	}
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public String view(Model model) {
		List<StudentDTO> stdList = sService.stdList();
		model.addAttribute("stdList", stdList);
		return "student/viewList";
	}
}
