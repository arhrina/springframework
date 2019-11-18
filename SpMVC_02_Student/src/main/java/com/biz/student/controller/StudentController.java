package com.biz.student.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // ui는 화면의 의미
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; // bind는 연결의 의미

import com.biz.student.domain.StudentDTO;
import com.biz.student.service.AnnService;
import com.biz.student.service.HomeService;
import com.biz.student.service.StudentService;
/*
 * @Controller
 * Tomcat, Spring container에 이 클래스의 list를 추가하고 request를 대기
 */

@Controller
public class StudentController { // 컨트롤러에서 쓰는 메소드는 모두 String형
	
	@Inject
	StudentService sService; // @ annontation들을 사용하지 않고 자바로 바꿈
	// bean에 선언된 클래스와 이름이 같지 않아도(과거엔 동일했어야 함) 되지만 클래스는 같아야한다
	// @Autowired는 Spring 고유방식, @Inject는 자바 EJB방식. inject는 과거의 방식
	
	@Autowired
	HomeService hService;
	
	@Autowired
	AnnService aService;
	
	public StudentController() {
		// TODO Auto-generated constructor stub
		sService = new StudentService();
	}
	@RequestMapping(value="input", method=RequestMethod.GET)
	public String input() {
		return "student/input"; 
	}
	// WEB-INF/views/student/input.jsp를 열어서 브라우저로 전송하라
	// value는 path. 겹치면 안됨. GET은 URI로 브라우저가 사용하며 POST는 form에서만 쓴다

	
	// input form에서 데이터를 입력한 후 전송을 하면 데이터를 수신할 메소드
	// 매개변수로 설정된 stDTO는 form input box에 설정된 name과 같은 변수명을
	// 필드로 가지는 DTO
	// 별다른 setter 조치 없이 spring framework는 자동으로 form의 input box에 설정된
	// 변수명 name값과 일치하는 정보를 검사하여 DTO에 값을 set한다
	
	// form에서 전달받은 모든 데이터는 기본적으로 String이며 이때 st_grade는 int형이므로
	// spring에서 문자열로 전달받은 데이터를 Inter.valueOf를 이용해 자동변환을 시도한다
	// st_grade에 값을 입력하지 않고 전송을 누르면 해당 변수엔 ""나 null이다
	// Integer.valueOf("")나 valueOf(null)은 NumberFormatException이 발생하고,
	// tomcat이 이를 받아 browser에 http 400 Error를 띄운다
	@RequestMapping(value="input", method=RequestMethod.POST)
	public String input(Model model, StudentDTO stDTO) {
		System.out.println(stDTO.toString());
		model.addAttribute("stDTO", stDTO);
		return "student/view"; // view.jsp를 열어라. 모델을 넘겨줘서 랜더링을 시작
	}
	
	@RequestMapping(value="search", method=RequestMethod.GET)
	public String search() {
		sService.viewStudent();
		hService.viewHome();
		aService.viewAnn();
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
