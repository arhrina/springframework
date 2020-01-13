package com.biz.rbooks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.rbooks.domain.RBVO;
import com.biz.rbooks.service.RBService;

@RequestMapping(value="/rbooks")
@Controller
public class RBController {
	
	@Autowired
	RBService rbService;
	
	@RequestMapping(value="/rblist", method=RequestMethod.GET)
	public String rblist(String b_code, Model a) {
		List<RBVO> rblist = rbService.findByBCode(b_code);
		a.addAttribute("rblist", rblist);
		return "rblist";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert() {
		return "insertRB";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(RBVO rbVO, String b_code) {
		rbVO.setRb_bcode(b_code);
		rbService.insert(rbVO);
		return "redirect:/rblist";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(String seq) {
		RBVO rbVO = new RBVO();
		rbVO = rbService.findBySEQ(seq);
		rbService.delete(rbVO);
		return "redirect:/rblist";
	}
}
