package com.biz.iolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.biz.iolist.domain.ProductDTO;
import com.biz.iolist.service.ProductService;

@RequestMapping(value="/product")
@Controller
public class ProductController {
	
	@Autowired
	ProductService pService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(Model model) {
		ModelAndView mView = new ModelAndView();
		List<ProductDTO> pList = pService.getAllList();
		mView.setViewName("/product/list");
		mView.addObject("PRODUCTLIST", pList);
		return mView;
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(String p_code, Model model) {
		ProductDTO pDTO = pService.findByPCode(p_code);
		model.addAttribute("PRO_DTO", pDTO);
		return null;
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String input(Model model) {
		return "product/input";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String input(ProductDTO pDTO, Model model) {
		int ret = pService.insert(pDTO);
		return "redirect:/product/list";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(String id, Model model) {
		int let = pService.delete(id);
		return "redirect:/dept/list";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String update(String id, Model model) {
		ProductDTO pDTO = pService.findByPCode(id);
		model.addAttribute("PI",pDTO);
		return "product/input";
	}

	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(ProductDTO pDTO) {
		int ret = pService.update(pDTO);
		return "redirect:/product/view?p_code=" + pDTO.getP_code();
	
	}
	
}
