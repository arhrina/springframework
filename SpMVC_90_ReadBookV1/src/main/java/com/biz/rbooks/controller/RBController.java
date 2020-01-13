package com.biz.rbooks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.rbooks.domain.RBVO;
import com.biz.rbooks.service.RBService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value="/rbooks")
@Controller
public class RBController {
	
	@Autowired
	RBService rbService;
	
	@RequestMapping(value="/rblist", method=RequestMethod.GET)
	public String rblist(String b_code, Model a) {
		List<RBVO> rblist = rbService.findByBCode(b_code);
		log.debug(rblist.toString());
		a.addAttribute("list", rblist);
		return "rblist";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(@RequestParam("seq") String b_code, Model a) {
		a.addAttribute("bcode", b_code);
		return "insertRB";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute RBVO rbVO) {
		rbVO.setRb_bcode(rbVO.getRb_bcode());
		log.debug(rbVO.toString());
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
