package com.biz.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.biz.shop.domain.ProductVO;
import com.biz.shop.repository.ProductDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {
	
	private final ProductDao pDao;
	
	public void save(ProductVO productVO) {
		ProductVO p = pDao.save(productVO);
		log.debug("상품정보: " + p.toString());
	}
	
	public List<ProductVO> selectAll() {
		List<ProductVO> proList = pDao.findAll();
		return proList;
	}

	public ProductVO findById(long id) {
		// TODO Auto-generated method stub
		// hibernate의 기본 조회 메서드들은 모든 VO클래스에 NPE를 방지하기 위한 Optional을 감싸 리턴한다
		// 실제 VO만을 추출할 때는 ret.get()을 사용한다
		Optional<ProductVO> pVO = pDao.findById(id);
		return pVO.get();
	}
	
	public ProductVO findByPCode(String p_code) {
		// TODO Auto-generated method stub
		return null;
	}


}







