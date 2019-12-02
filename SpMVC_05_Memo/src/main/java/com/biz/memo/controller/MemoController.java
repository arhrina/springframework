package com.biz.memo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.biz.memo.domain.MemoDTO;
import com.biz.memo.service.MemoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value="/memo")
@SessionAttributes("memoDTO")
@Controller
public class MemoController {

/* SessionAttributes로 설정된 값은 view한테 보내면서 서버가 따로 보관해둔다
 * input box에서 값을 넣은 값들과 만약 input box에서 값이 없다면 메모리에서 해당하는 필드들을 가져와서 세팅한다
 * tag가 없어도 값을 유지한다. browser와 server가 한번이라도 연결을 했었다면 SessionAt...에 등록된 변수는 서버가 중단되기 전까지
 * 값이 유지된다
 * 
 * web은 특성상 클라이언트의 request를 서버가 받아서 response를 수행하고 나면 모든 정보가 사라지는 특성을 갖는다
 * 이런 특성과 달리 spring 기반 web은 일부 데이터들을 메모리에 보관했다가 재사용하는 기법이 여러개 있으며 그 중 하나가 SessionAttributes
 */
	@ModelAttribute("memoDTO")
	public MemoDTO makeMemoDTO() {
		MemoDTO memoDTO = new MemoDTO();
		return memoDTO;
	}
/*
 * SessionAttributes를 사용하려면 반드시 해당 변수를 생성하는 method가 controller에 있어야하고,
 * 해당 method에 ModelAttribute("변수명")으로 설정해줘야한다 
 */
	
	@Autowired
	MemoService mService;
	
	// RequestMapping에서 뒤에 method를 생략할 수 있게 해준다. 뒤에 GET, POST에 대한 것. 5.2버전 이후로는 앞으로 없어질 예정
	@GetMapping
	@PostMapping

	@RequestMapping(value="/list", method=RequestMethod.GET)
	// URL에 /memo/list를 입력하고 들어오면(메소드가 GET) views 폴더에 home.jsp를 호출한다
	public String list(String search, Model model) {
		List<MemoDTO> memoList;
		if(search == null || search.isEmpty()) {
			memoList = mService.getAllList();
		}
		else {
			memoList = mService.getSearchList(search);
		}
			
		model.addAttribute("MEMO_LIST", memoList);
		/*
		for(MemoDTO m : memoList) {
			log.debug(m.toString());
		}
		*/
		return "home";
	}
	
	/*
	 * SessionAttributes에서 설정한 변수는 @ModelAttribute를 설정해줘야한다
	 * 5 이하에서는 필수사항, 5 이상에서는 선택
	 */
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(@ModelAttribute("memoDTO") MemoDTO memoDTO, Model model) {
		memoDTO = MemoDTO.builder().m_seq(9999).m_date("2019-12-02").m_time("15:30:00").m_auth("LLLLL").m_text("KOREA").build();
		model.addAttribute("memoDTO", memoDTO);
		return "insert";
	}
	
	/*
	 * insert POST가 memoDTO를 수신할 때 입력form에서 사용자가 입력한 값들이 있으면 그 값들을 memoDTO의 field에 set하고
	 * 값들이 없으면 메모리 어딘가에 보관중인 SessionAttribute로 설정된 memoDTO에서 값을 가져와서 비어있는 field를 채운다
	 * 
	 * form에서 보이지 않아도 되는 값들은 별도의 코딩을 하지 않아도 자동으로 POST되는 효과를 낸다
	 * 이 기능을 효율적으로 쓰려면 jsp코드에서 spring-form tag로 input box들을 코딩해야한다
	 */
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute("memoDTO") MemoDTO memoDTO, String search, Model model){
		log.debug("시퀀스 : " + memoDTO.getM_seq());
		log.debug("날짜 : " + memoDTO.getM_date());
		log.debug("텍스트 : " + memoDTO.getM_text());
		int ret = mService.insert(memoDTO);
		return "redirect:/memo/list";
	}
}
