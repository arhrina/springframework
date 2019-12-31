package com.biz.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.todo.domain.ToDoList;
import com.biz.todo.service.ToDoService;

@Controller
public class ToDoController {

	@Autowired
	@Qualifier("toDoServiceV2") // factory Bean Qualifier
	// ToDoService toService; // bean은 2개가 올 수 없다. 에러 발생
	// ToDoService toDoServiceV2; // 상속을 받으면 Controller에서 모든 이름을 바꿔줘야하는 일이 발생
	ToDoService toService; // 서비스에 이름을 붙이고 qualifier annotation으로 어떤 서비스인지 지정해줄 수 있다
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public String list(Model model) {
		/*
		 * 팀프로젝트에서 Controller 개발자와, Service 개발자가 다를경우
		 * Service는 interface가 정의 되어 있기 때문에
		 * 아직 기능은 구현되지 않았어도
		 * Controller 개발자는 당연히 Service의 method를 호출하면
		 * 결과가 리턴될것이다 라는 것을 알고
		 * 나머지 코드를 구현할수 있게 된다.
		 */
		List<ToDoList> toDoList = toService.selectAll();
		model.addAttribute("todoList", toDoList);
		return "home";
	}
	
	@RequestMapping(value="list",method=RequestMethod.POST)
	public String insert(@ModelAttribute ToDoList toDoList,
					Model model) {
		
		int ret = toService.insert(toDoList);
		if(ret < 1) {
			model.addAttribute("INSERT_ERROR","NOT_INSERT");
		}
		return "redirect:/list";
	}
	
	@RequestMapping(value="complete", method=RequestMethod.GET)
	public String complete(@RequestParam("tdSeq") String strSeq,
			@RequestParam("tdComplete") String strComplete) {
		toService.complete(strSeq, strComplete);
		return "redirect:/list";
	}
	
	@RequestMapping(value="alarm", method=RequestMethod.GET)
	public String alarm(@RequestParam("tdSeq") String strSeq,
			@RequestParam("tdAlarm") String strAlarm) {
		toService.alarm(strSeq, strAlarm);
		return "redirect:/list";
	}
	
}
