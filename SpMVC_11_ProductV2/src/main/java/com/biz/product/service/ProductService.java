package com.biz.product.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.product.domain.ProductDTO;
import com.biz.product.persistence.ProductDao;

@Service
public class ProductService {
	
	@Autowired
	SqlSession sqlSession;
	
	ProductDao proDao;
	
	@Autowired
	public void proDao() {
		this.proDao = sqlSession.getMapper(ProductDao.class);
	}
	
	
	public ProductDTO findByPCode(String p_code) {
		return proDao.findByPCode(p_code);
	}
	
	public List<ProductDTO> findByPNames(String p_name) {
		return proDao.findByPNames(p_name);
	}


	public List<ProductDTO> selectAll() {
		return proDao.selectAll();
	}


	public int insert(ProductDTO proDTO) { // 상품이 추가되었을 때 가장 큰 PCode를 가져와서 숫자부분을 1 더하는 문자열 생성  
		// TODO Auto-generated method stub
		String p_code = proDao.getMaxPCode();
		String p_preCode = "P";
		
		int intPCode = 1; // 상품테이블에 데이터가 없으면 intPCode를 1로 놓고 진행된다
		try { // 상품테이블에 데이터가 하나도 없는 경우, try문 error 발생
			p_preCode = p_code.substring(0, 1);
			String p_sufCode = p_code.substring(1);
			intPCode = Integer.valueOf(p_sufCode) + 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		p_code = String.format("%s%04d", p_preCode , intPCode);
		proDTO.setP_code(p_code);
		
		return proDao.insert(proDTO);
	}

	public int update(ProductDTO proDTO) {
		// TODO Auto-generated method stub
		return proDao.update(proDTO);
	}

	
}
