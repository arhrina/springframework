package com.biz.product.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biz.product.domain.ProductDTO;
import com.biz.product.service.ProductService;

@RequestMapping(value="/rest")
@RestController // 이 컨트롤러는 RESTful 서비스를 response하겠다는 선언. 모든 method는 view를 return 할 수 없다
// Model, ModelAndView class를 사용하지 않아도 된다
public class ProductRestController {
	
	@Autowired
	ProductService pService;
	
	@RequestMapping(value="/getProduct",
			method=RequestMethod.GET,
			produces = "application/json;charset=UTF-8")
	public ProductDTO getProduct(String p_code) {
		ProductDTO proDTO = pService.findByPCode(p_code);
		
		return proDTO;
	}
	
	@RequestMapping(value="/getString", method=RequestMethod.GET, produces="text/json;charset=UTF-8")
	public String getString(@RequestParam(value="str", // query로 보내는 변수명
	// required=false와 defaultValue가 없으면 server는 client에게 400 오류를 보내고 처리를 거부. vo와 dto에는 적용할 수 없음
	required=false,// 혹시 값을 보내지 않아도 오류를 내지마라
	defaultValue="없음" // 값이 없으면 없음이라는 문자열을 세팅
	)String myStr) {
		if(myStr.equals("없음")) {
			return "http://localhost:8080/product/getString?str=문자열 형식으로 보내세요";
		}
		else {
			return "보낸 문자열은 " + myStr;
		}
	}
	
	@RequestMapping(value="/getUUID", method=RequestMethod.GET, produces="text/json;charset=UTF-8")
	public String getUUID() {
		String strUUID = UUID.randomUUID().toString(); // 고유한 32byte의 숫자로 된 ID를 생성해서 문자열로 만든다
		return strUUID + ": " + strUUID.length();
	}
}
