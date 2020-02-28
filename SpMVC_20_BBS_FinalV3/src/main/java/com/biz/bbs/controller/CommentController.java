package com.biz.bbs.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.bbs.domain.CommentVO;
import com.biz.bbs.service.CommentService;

import lombok.RequiredArgsConstructor;

/**
 * class에 붙이는 RequestMapping
 * 
 * type 수준의 RequestMapping, top level의 RequestMapping 등으로 부른다
 * 해당 클래스에서 만드는 메서드들의 requestMapping value앞에 prefix로 붙는다
 */
@RequestMapping(value="/comment")
@Controller
@RequiredArgsConstructor
public class CommentController {

	private final CommentService cService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(String b_id, Model model) { // 게시판의 id값을 매개변수로 받아 댓글 리스트를 보여줌
		long c_b_id = Long.valueOf(b_id);
		List<CommentVO> cmtList = cService.findByBId(c_b_id);
		model.addAttribute("cList", cmtList);
		return "comment_list";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String insert(@ModelAttribute CommentVO cVO, Model model) { // 입력값들을 매개변수로 받아 댓글 추가
		cService.insert(cVO);
		
//		long c_b_id = cVO.getC_b_id();
//		return "redirect:/detail?b_id=" + c_b_id; // redirect에도 값을 query문으로 붙일 수 있다. 일반적인 방법
		
		
		model.addAttribute("b_id", cVO.getC_b_id());
//		return "redirect:/detail"; // redirect return 전에 모델을 붙여주면 URI에 자동으로 쿼리가 붙어 넘어간다. 여러개 가능
		return "redirect:/comment/list";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST) // ajax에서 POST로 보냈으므로 POST로 받아야한다
	public String delete(@RequestParam String c_id, @RequestParam String b_id, Model model) {
		int ret = cService.delete(Long.valueOf(c_id));
		model.addAttribute("b_id", b_id);
		return "redirect:/comment/list";
	}
}
