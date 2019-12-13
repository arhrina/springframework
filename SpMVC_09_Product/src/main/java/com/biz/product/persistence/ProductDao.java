package com.biz.product.persistence;

import java.util.List;

import com.biz.product.domain.ProductDTO;

public interface ProductDao {
	public List<ProductDTO> selectAll();
	public ProductDTO findByPCode(String pCode);
	public List<ProductDTO> findByPNames(String pName);
	public int insert(ProductDTO pDTO);
	public int update(ProductDTO pDTO);
	public int delete(String pCode);
}
