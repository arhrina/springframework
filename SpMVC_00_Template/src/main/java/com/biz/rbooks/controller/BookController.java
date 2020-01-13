package com.biz.rbooks.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.rbooks.domain.BookVO;
import com.biz.rbooks.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value="/books")
@Controller
public class BookController {
	@Autowired
	BookService bService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String showList(Model model) {
		List<BookVO> bVO = bService.selectAll();
		log.debug(bVO.toString());
		model.addAttribute("bList", bVO);
		return "list";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert2() {
		return "insert";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute("bVO") BookVO bVO, Model model) {
		log.debug(bVO.toString());
		bService.insertBook(bVO);
		return "redirect:/";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(Model a, @RequestParam("b_code") String bCode) {
		BookVO bVO = bService.findByBCode(bCode);
		a.addAttribute("bookVO", bVO);
		return "insert";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@ModelAttribute BookVO bVO) {
		bService.updateBook(bVO);
		return "redirect:/";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(Model model, BookVO bVO) {
		bService.deleteBook(bVO.getB_code());
		return "redirect:/";
	}
	
//	@RequestMapping(value="/search", method=RequestMethod.GET)
//	public String searchList(@RequestParam("b_name") String b_name, @RequestParam("b_author") String b_author, Model a) {
//		List<BookVO> bVO = new ArrayList<BookVO>();
//		bVO = bService.findByNameAndAuthor(b_name, b_author);
//		a.addAttribute("bList", bVO);
//		return "searchList";
//	}
}
