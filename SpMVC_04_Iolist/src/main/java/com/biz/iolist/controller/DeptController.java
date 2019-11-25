package com.biz.iolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.biz.iolist.domain.DeptDTO;
import com.biz.iolist.service.DeptService;

@RequestMapping(value="/dept")
@Controller
public class DeptController {
	
	@Autowired // @Inject와 같은 효과지만 스프링은 오토와이어를 쓰는 것이 낫다
	DeptService dService;

	// class에 있는 dept와 method에 있는 list를 묶어서 /dept/list path로 request했을 때 list() 호출

	// routing. controller에 main path를 지정하고 method에 subpath를 지정해서 path 관리
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list() {
		/*
		 * ModelAndView는 Model(ui.Model) 클래스와 같은 역할을 수행하는 Spring class
		 * view와 객체를 동시에 담아서 Despatcher에게 객체를 return
		 * 
		 * Model은 객체만 담고 View는 문자열을 return하여 사용
		 * 
		 * 개발자의 취향으로 선택해서 사용
		 */
		List<DeptDTO> deptList = dService.getAllList();
		ModelAndView mView = new ModelAndView();
		mView.setViewName("/dept/list"); // return "dept/list"와 같은 역할
		mView.addObject("DEPTLIST", deptList); // model.addAttribute("DEPTLIST", deptList)와 같은 역할
		
		// return ModelAndView를 사용. String이 아니다
		return mView;
	}
}
