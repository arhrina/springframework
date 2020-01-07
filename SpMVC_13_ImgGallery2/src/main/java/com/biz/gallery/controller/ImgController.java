package com.biz.gallery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.biz.gallery.domain.ImageFilesVO;
import com.biz.gallery.domain.ImageVO;
import com.biz.gallery.service.ImageServiceV2;

import lombok.extern.slf4j.Slf4j;

@SessionAttributes({"imageVO"})
@Slf4j
@RequestMapping(value="/image")
@Controller
public class ImgController {
	
	 @Qualifier("imgServiceV2")
	  ImageServiceV2 imgService;
	  
	  @Autowired
	  public ImgController(ImageServiceV2 imgService) {
	      // TODO Auto-generated constructor stub
	      this.imgService = imgService;
	   }
	
	@ModelAttribute("imageVO")
	public ImageVO newImageVO() {
		return new ImageVO();
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		List<ImageVO> imgList = imgService.selectAll();
		model.addAttribute("imgList", imgList);
		return "home";
	}
	
	@RequestMapping(value="/upload",method=RequestMethod.GET)
	public String upload(
			@ModelAttribute("imageVO") ImageVO imageVO,
			Model model) {
		log.debug("이미지 업로드 시작!!");
		
		model.addAttribute("BODY","UPLOAD");
		model.addAttribute("imageVO",imageVO);
		return "home";
	}
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String upload(@ModelAttribute("imageVO") ImageVO imageVO, SessionStatus status) {
	
		log.debug("업로드 : " + imageVO.toString());
		
		imgService.insert(imageVO);
		status.setComplete();
		return "redirect:/image/list";
	}
	
	@RequestMapping(value="/update/{img_seq}", method=RequestMethod.GET)
	public String update(@PathVariable("img_seq") String data_seq, Model model) {
		
		// ?변수=값 형식으로 전송하고 변수에서 받아 사용하는 방법은 requestParam을 이용
		// pathVariable은 path/변수로 전송해서 변수에서 수신
		
		log.debug(data_seq);
		
		ImageVO imgVO = imgService.findBySeq(data_seq);
		model.addAttribute("BODY", "UPLOAD");
		model.addAttribute("imageVO", imgVO);
		return "home";
	}
	
	@RequestMapping(value="/update/{img_seq}", method=RequestMethod.POST)
	public String update(@ModelAttribute("imageVO") ImageVO imgVO,SessionStatus status) {
		// 이미지 이름이 기존의 이미지와 다르면 기존 이미지를 삭제해줘야한다. 서비스에서 해당기능 구현
		int ret = imgService.update(imgVO);
		
		status.setComplete();
		return "redirect:/image/list";
	}
	
	/*
	 * MultipartHttpServletRequest
	 * 다중파일 수신하기
	 */
	@RequestMapping(value="/files_up", method=RequestMethod.POST)
	public String files_up(MultipartHttpServletRequest mFiles, Model model) {

		List<ImageFilesVO> fileNames = imgService.files_up(mFiles);
		
		model.addAttribute("imgList", fileNames);
		return "include/img_card_box";
		
	}
}
