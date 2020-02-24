package com.biz.shop.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.shop.domain.CartVO;
import com.biz.shop.domain.ProductVO;
import com.biz.shop.service.CartService;
import com.biz.shop.service.ProductService;

@RequestMapping(value="/user/product")
@Controller
public class B2C_Controller {
	
	@Autowired
	ProductService pService;
	@Autowired
	CartService cService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model) {
		List<ProductVO> productList = pService.selectAll();
		model.addAttribute("B2C_LIST", productList);
		return "users/user_main";
	}
	
	@RequestMapping(value="/detail/{id}",method=RequestMethod.GET)
	public String detail(@PathVariable("id") String pId, Model model) {
		long lPId = Long.valueOf(pId);
		ProductVO pVO = pService.findById(lPId);
		model.addAttribute("BODY", "DETAIL");
		model.addAttribute("pVO", pVO);
		return "users/user_main";
	}
	
	/*
	 * Authentication : 스프링 시큐리티로 로그인된 사용자 정보를 추출할 때 사용하는 인터페이스
	 */
	@ResponseBody
	@RequestMapping(value="/cart", method=RequestMethod.POST)
	public String cart(CartVO cartVO, Authentication authen) {
		
		try {
			// 스프링 시큐리티로 로그인한 사용자의 username을 추출
			cartVO.setUsername(authen.getPrincipal().toString());
		} catch (Exception e) {
			// TODO: handle exception
			return "LOGIN_FAIL";
		}
		cService.save(cartVO);
		return "OK";
		// return cartVO;
		// return "LOGIN USER : " + authen.getPrincipal();
	}
	
	@RequestMapping(value="/cart_view", method=RequestMethod.GET)
	public String cartView(Authentication authen, Model model) {
		
		model.addAttribute("BODY", "CART_VIEW");
		try {
			String username = authen.getPrincipal().toString();
			List<CartVO> cartList = cService.selectCart(username);
			model.addAttribute("CART_LIST", cartList);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "users/user_main";
	}
	
}
