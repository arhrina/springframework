package com.biz.shop.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.shop.domain.ProductVO;
import com.biz.shop.service.ProductService;

@RequestMapping(value="/user/product")
@Controller
public class B2C_Controller {
	
	@Autowired
	ProductService pService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model) {
		List<ProductVO> productList = pService.selectAll();
		model.addAttribute("B2C_LIST", productList);
		return "users/user_product_list";
	}
	
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String detail(String pId, Model model) {
		long lPId = Long.valueOf(pId);
		ProductVO pVO = pService.findById(lPId);
		model.addAttribute("pVO", pVO);
		return "users/user_product_detail";
	}
}
