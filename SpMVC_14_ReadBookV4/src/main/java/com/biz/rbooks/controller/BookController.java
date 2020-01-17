package com.biz.rbooks.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.rbooks.domain.BookVO;
import com.biz.rbooks.service.BookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping(value="book") // /를 앞에 넣어주지 않아도 path가 알아서 /해서 쪼개준다. 스프링 5.0 이상
public class BookController {
	
	private final BookService bService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		List<BookVO> bookList = bService.selectAll();
		model.addAttribute("b_List", bookList);
		return "books/list";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(BookVO bookVO) {
		bService.insert(bookVO);
		return "redirect:/book/list";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String search(@RequestParam(value="strText", required=false, defaultValue="") String text, Model model) {
		// RequestParam으로 1개의 값을 받아서 text라는 이름으로 이 메서드에서 쓰기위한 선언. 값을 안넣으면 ""으로 검색해서 전부 가져옴
		List<BookVO> bookList = bService.findByBNames(text);
		model.addAttribute("b_List", bookList);
		return "books/list-body";
	}
	
	
	
//	
//	@RequestMapping(value="/babo", method=RequestMethod.GET)
//	public String babo(Model model) {
//		String a = "이희현은바보인가";
//		model.addAttribute("w", a);
//		return "똑똑한/이희현";
//		// /WEB-INF/views/똑똑한/이희현
//	}
//	
//	@RequestMapping(value="/babo", method=RequestMethod.POST)
//	public String babo2(String 똑똑해, Model model) {
//		model.addAttribute("이희현", 똑똑해);
//		return "바보/이희현";
//	}
}
