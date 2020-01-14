package com.biz.rbooks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.rbooks.domain.ReadBookVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value="/rbook")
@Controller
public class ReadBookController {

	/*
	 * localhost:8080/rbooks/rbook/insert를 입력했을 때 입력화면을 보여주기 위한 메서드
	 */
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(Model model) {
		ReadBookVO rBookVO = new ReadBookVO();
		model.addAttribute("rBookVO", rBookVO);
		return "rbooks/input";
	}
}
