package com.biz.bok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.biz.bok.domain.BokSearchDTO;
import com.biz.bok.service.BokService;
import com.biz.bok.service.CodeService;

/*
 * 기억장치 어딘가에 bokSearchDTO라는 객체 저장 공간을 만들고, 그 공간을 Session에 등록하여 controller나 jsp 어디에서든 접근하도록
 * 설정해주는 것이 @SessionAttributes
 * 
 * Session에 등록한다 : 서버의 기억장치에 저장하여 client(web browser)와 서버간의 연결이 끊겨도 데이터를 참조할 수 있도록
 * 
 * HTTP PROTOCOL의 특징
 * web form에 입력된 data가 server로 전송되면 그 데이터는 사라진다
 * server에서 web browser에 결과를 보내고 나면 그 데이터는 사라지고 연결도 종료된다
 * 
 * web과 server간의 어떤 데이터를 일정하게 유지하고 싶을 때(login)는 Session이라는 공간에 data를 저장해두는 것으로 해결할 수 있다
 * Session은 web browser와 server가 공유하는 데이터라고 표현하기도 한다
 */
@SessionAttributes("bokSearchDTO")
@Controller
public class BokController {
	
	@Autowired
	CodeService cService;
	@Autowired
	BokService bService;
	
	// SessionAttributes를 설정하면 해당하는 객체변수는 현재 컨트롤러 내에서 반드시 생성하는 메서드가 있어야한다
	// SessionAttributes에 자료형을 등록해주는 것
	@ModelAttribute("bokSearchDTO")
	public BokSearchDTO bokSearchDTO() {
/*		BokSearchDTO bkDTO = BokSearchDTO.builder()
				.searchWrd("고용정책")
				.build();
				*/
		return bService.getDefaultSearch();
	}
	
	/*
	 * 웹에서 search를 request하면 매개변수 bokSearchDTO를 어딘가로부터 받아야하는데, 최초에는 이 값이 없는 상태로 search를 호출
	 * 이럴 경우 bokSearchDTO() 메서드가 자동으로 호출되어 사용할 수 있도록 생성, 초기화(initialize) 해준다
	 */
	@RequestMapping(value="search", method=RequestMethod.GET)
	public String search(@ModelAttribute("bokSearchDTO") BokSearchDTO bokSearchDTO, Model model) {
		model.addAttribute("bokSearchDTO", bokSearchDTO);
		model.addAttribute("SeMap", cService.getSelectMaps());
		return "home";
	}
	
	@ResponseBody
	@RequestMapping(value="search", method=RequestMethod.POST)
	public String search(@ModelAttribute("bokSearchDTO") BokSearchDTO bokSearchDTO, Model model, String strDummy) {
		bokSearchDTO.setSearchWrd("청년정책");
		model.addAttribute("bokSearchDTO", bokSearchDTO);
		model.addAttribute("SeMap", cService.getSelectMaps());
		return bService.getRestResult(bokSearchDTO);
		// return "home";
	}
}
