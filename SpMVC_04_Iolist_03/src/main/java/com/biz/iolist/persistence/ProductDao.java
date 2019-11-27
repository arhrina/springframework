package com.biz.iolist.persistence;

import java.util.List;

import com.biz.iolist.domain.ProductDTO;

public interface ProductDao {
	List<ProductDTO> selectAll();
	String getPCode();
	int insert(ProductDTO pDTO);
	ProductDTO findByPCode(String p_code);
	int delete(String p_code);
	int update(ProductDTO pDTO);
}
