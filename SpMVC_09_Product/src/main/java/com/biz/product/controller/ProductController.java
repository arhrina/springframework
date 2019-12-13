package com.biz.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.product.domain.ProductDTO;
import com.biz.product.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService pService;
	
	// @ResponseBody
	@RequestMapping(value="plist", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getPlist(Model model) {
		List<ProductDTO> proList = pService.selectAll();
		model.addAttribute("PLIST", proList);
		return "p-list"; // API처럼 자료를 뿌려준다
	}
	
	@ResponseBody
	@RequestMapping(value="plist/name", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public List<ProductDTO> getNames(String p_name) {
		List<ProductDTO> proList = pService.findByPName(p_name);
		return proList; // API처럼 자료를 뿌려준다. 그 자체가 view를 가지고 있지 않고 요청한 데이터만을 return. json을 return
	}

	@ResponseBody
	@RequestMapping(value="product", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ProductDTO getProduct(String p_code) {
		ProductDTO proDTO = pService.findByPCode(p_code);
		return proDTO; // json으로 produces가 설정되어 있으면 서버로 객체를 json으로 보내려고 시도한다
		// java는 이걸 해결할 수 없기 때문에 maven repository에서 jackson을 검색해서 databind를 maven에 올려준다
	}
	/*
	 * produces의 content-type
	 * 서버에서 문자열, 객체 등의 실제 데이터를 response할 때 어떤 타입으로 보낼 것인가를 나타내는 문자열
	 * MIME 타입에 대해 검색
	 */
	@ResponseBody
	@RequestMapping(value="pname", method=RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String getPName(String p_code) {
		ProductDTO proDTO = pService.findByPCode(p_code);
		// return proDTO.getP_name();
		return "<h1>" + proDTO.getP_name() + "</h1>";
	}
	
	@ResponseBody
	@RequestMapping(value="oprice", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String getOPrice(String p_code) {
		ProductDTO proDTO = pService.findByPCode(p_code);
		return proDTO.getP_oprice() + "";
	}
	
	@ResponseBody
	@RequestMapping(value="iprice", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String getIPrice(String p_code) {
		ProductDTO proDTO = pService.findByPCode(p_code);
		return proDTO.getP_iprice() + "";
	}
}
