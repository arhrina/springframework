package com.biz.friend.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.friend.domain.FriendVO;
import com.biz.friend.service.FriendService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class FriendController {
	
	private final FriendService fService;

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String viewList(Model model) {
		List<FriendVO> fList = fService.selectAllList();
		model.addAttribute("friend_list", fList);
		return "home";
	}
}
