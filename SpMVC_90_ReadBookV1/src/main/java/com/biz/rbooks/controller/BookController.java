package com.biz.rbooks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.rbooks.domain.BookVO;
import com.biz.rbooks.service.BookService;

@RequestMapping(value="/books")
@Controller
public class BookController {
	@Autowired
	BookService bService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String showList(Model model) {
		List<BookVO> bVO = bService.selectAll();
		model.addAttribute("bList", bVO);
		return "list";
	}
}
