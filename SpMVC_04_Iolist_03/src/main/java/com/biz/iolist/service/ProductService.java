package com.biz.iolist.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.iolist.domain.DeptDTO;
import com.biz.iolist.domain.ProductDTO;
import com.biz.iolist.persistence.DeptDao;
import com.biz.iolist.persistence.ProductDao;

@Service
public class ProductService {

	@Autowired
	SqlSession sqlSession;
	
	public List<ProductDTO> getAllList(){
		ProductDao pDao = sqlSession.getMapper(ProductDao.class);
		List<ProductDTO> pList = pDao.selectAll();
		return pList;
	}
	
	public int insert(ProductDTO pDTO) {

		ProductDao pDao = sqlSession.getMapper(ProductDao.class);
		
		/*
		 * 거래처코드 자동생성을 해서
		 * deptDTO의 d_code에 저장
		 */
		// D0900
		String p_code = pDao.getPCode();
		
		// 0900 만 추출
		String p_code_num = p_code.substring(1);
		
		//901번 값 생성
		int int_pcode = Integer.valueOf(p_code_num) + 1;
		
		// D 만추출
		p_code = p_code.substring(0,1);
		
		// "D" + "0901"
		p_code += String.format("%04d",int_pcode);
		pDTO.setP_code(p_code);
		int ret = pDao.insert(pDTO);
		return ret;
	}
	
	public ProductDTO findByPCode(String p_code) {
		ProductDao pDao = sqlSession.getMapper(ProductDao.class);
		ProductDTO pDTO = pDao.findByPCode(p_code);
		return pDTO;
	}
	
	public int delete(String p_code) {

		ProductDao pDao = sqlSession.getMapper(ProductDao.class);
		int ret = pDao.delete(p_code);
		return ret;
	}

	public int update(ProductDTO pDTO) {
		
		ProductDao pDao = sqlSession.getMapper(ProductDao.class);
		int ret = pDao.update(pDTO);
		return ret;
	
	}
}
