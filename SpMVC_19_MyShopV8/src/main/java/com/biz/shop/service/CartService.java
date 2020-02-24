package com.biz.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.shop.dao.CartDao;
import com.biz.shop.domain.CartVO;
import com.biz.shop.persistance.CartRepository;

@Service
public class CartService {

	@Autowired
	CartDao cDao;
	
	@Autowired
	CartRepository cRepository;
	
	public int countCart() {
		return cDao.countCart();
	}
	
	public int countDelivery() {
		return cDao.countDelivery();
	}
	
	public List<CartVO> selectCart(String username){
		return cDao.selectCart(username);
	}
	
	public List<CartVO> selectDelivery(String username){
		return cDao.selectDelivery(username);
	}

	public CartVO save(CartVO cartVO) {
		// TODO Auto-generated method stub
		cartVO.setP_status("CART");
		return cRepository.save(cartVO);
	}
}
