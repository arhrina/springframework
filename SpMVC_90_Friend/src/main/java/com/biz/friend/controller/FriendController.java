package com.biz.friend.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.friend.domain.FriendVO;
import com.biz.friend.service.FriendService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class FriendController {
	
	private final FriendService fService;

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String viewList(Model model) {
		List<FriendVO> fList = fService.selectAllList();
		model.addAttribute("friend_list", fList);
		return "list";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String doSearch(@RequestParam String fName, @RequestParam String fTel, Model model) {
		List<FriendVO> fList = null;
		if(!(fName.isEmpty() || fName == "")) {
			fList = fService.findByName(fName);
		}
		if(!(fTel.isEmpty() || fTel == "")) {
			fList = fService.findByTel(fTel);
		}
		model.addAttribute("search_list", fList);
		return "search-list";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String input() {
		return "insert";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String input(@ModelAttribute FriendVO fVO) {
		log.debug(fVO.toString());
		int ret = fService.insert(fVO);
		return "redirect:/list";
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String viewPage(@RequestParam long f_id, Model model) {
		FriendVO fVO = fService.findById(f_id);
		model.addAttribute("friendVO", fVO);
		return "view";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String deleteCol(@RequestParam long f_id) {
		int ret = fService.delete(f_id);
		return "redirect:/list";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(@RequestParam long f_id, Model model) {
		FriendVO fVO = fService.findById(f_id);
		model.addAttribute("vo", fVO);
		return "insert";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String 개짜증나(@ModelAttribute FriendVO fVO) {
		log.debug(fVO.toString());
		int 리턴 = fService.update(fVO);
		return "redirect:/list";
	}
}
