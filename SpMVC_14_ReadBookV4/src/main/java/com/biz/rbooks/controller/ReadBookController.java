package com.biz.rbooks.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.rbooks.domain.BookVO;
import com.biz.rbooks.domain.ReadBookVO;
import com.biz.rbooks.service.BookService;
import com.biz.rbooks.service.ReadBookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // private final 필드는 자동으로 생성자를 만들고 autowired 시켜주는 lombok annotation
@RequestMapping(value="/rbook")
@Controller
public class ReadBookController {
	
	private final ReadBookService rBookService;
	private final BookService bService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		List<ReadBookVO> rBookList = rBookService.selectAll();
		model.addAttribute("RBOOKS", rBookList);
		return "rbooks/list";
	}

	/*
	 * localhost:8080/rbooks/rbook/insert를 입력했을 때 입력화면을 보여주기 위한 메서드
	 */
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(Model model) {
		ReadBookVO rBookVO = new ReadBookVO();
		// java 1.8 이상에서 사용할 수 있는 날짜 클래스
		LocalDate localDate = LocalDate.now();
		
		String curDate = localDate.toString(); // 날짜를 문자열로 만들고
		rBookVO.setRb_date(curDate); // 날짜를 VO에 담아서 jsp에서 따로 입력받지 않고 현재 시간값이 jsp에 넘어가서 처리
		
		
		// 시간을 시:분:초 만 보이도록 설정. HH는 24시간제, hh는 12시간제
		DateTimeFormatter dt = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime localTime = LocalTime.now();
		String curTime = localTime.format(dt).toString();
		rBookVO.setRb_stime(curTime);
		// 마찬가지로 시간값을 넣어서 글쓰기 눌러서 localhost:8080/rbooks/rbook/insert를 누르면
		// return으로 rbooks/input.jsp를 부를 때 jsp에 시간이 자동으로 들어가도록
		
		model.addAttribute("rBookVO", rBookVO);
		return "rbooks/input";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(ReadBookVO rBookVO) {
		int ret = rBookService.insert(rBookVO);
		return "redirect:/"; // localhost:8080/rbooks/ URL로 보내는 메시지
	}
	
	@RequestMapping(value="/view/{seq}", method=RequestMethod.GET) // list.jsp JS에서 클릭동작시 seq가 GET으로 날아온 것으로
	// 검색하고 검색된 VO값을 r_book이란 이름으로 view.jsp로 날려준다. PathVariable은 value에 중괄호를 사용해서 받는다
	public String view(@PathVariable("seq") long rb_seq, Model model) {
		
		ReadBookVO rBookVO = rBookService.findBySeq(rb_seq); // get한 seq로 자료값 찾기
		model.addAttribute("r_book", rBookVO);
		
		String bCode = rBookVO.getRb_bcode(); // seq로 찾은 자료의 bcode만 뽑아내기
		BookVO bVO = bService.findByBCode(bCode); // 뽑아낸 bcode로 책정보 찾기
		model.addAttribute("book", bVO);
		
		List<ReadBookVO> rBookList = rBookService.findByBCode(bCode);
		model.addAttribute("RBOOKS", rBookList);
		return "rbooks/view";
	}
	
	@RequestMapping(value="/update/{seq}", method=RequestMethod.GET) // list.jsp JS에서 클릭동작시 seq가 GET으로 날아온 것으로
	// 검색하고 검색된 VO값을 r_book이란 이름으로 view.jsp로 날려준다. PathVariable은 value에 중괄호를 사용해서 받는다
	public String update(@PathVariable("seq") long rb_seq, Model model) {
		ReadBookVO rBookVO = rBookService.findBySeq(rb_seq);
		model.addAttribute("rBookVO", rBookVO);
//		String bCode = rBookVO.getRb_bcode();
//		BookVO bVO = bService.findByBCode(bCode);
//		model.addAttribute("book", bVO);
		return "rbooks/input";
	}
	
	@RequestMapping(value="/update/{seq}", method=RequestMethod.POST)
	// 수정을 눌렀을 때 전해져오는 seq값을 사용하기 위해 value에 {변수명} 사용
	public String update(@ModelAttribute ReadBookVO rBookVO) {
		int ret = rBookService.update(rBookVO);
		return "redirect:/rbook/list";
	}
	
	@RequestMapping(value="/delete/{seq}", method=RequestMethod.GET)
	public String delete(@PathVariable("seq") String rb_seq) { // 어노테이션 이름은 value에 붙는것과 같아야한다
		int ret = rBookService.delete(Long.valueOf(rb_seq));
		return "redirect:/rbook/list";
	}
}
