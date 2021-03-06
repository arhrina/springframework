package com.biz.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.biz.shop.domain.CartVO;

public interface CartDao {

	// 장바구니 보기용 SELECT
	@Select("SELECT P.p_code, P.p_name, C.username, C.p_oprice, C.p_qty FROM tbl_cart C "
			+ " LEFT JOIN tbl_product P ON C.p_code = P.p_code "
			+ " WHERE username = #{username} AND p_status = 'CART' ")
	public List<CartVO> selectCart(@Param("username") String username);
	
	// 관리자가 현재 카트에 몇건 담겨있는지 조회하는 SELECT
	@Select("SELECT count(*) FROM tbl_cart WHERE p_status = 'CART' ")
	public int countCart();
	
	// 주문완료 후 배송중인 SELECT
	@Select("SELECT * FROM tbl_cart WHERE username = #{username} AND p_status = 'DELIVERY' ")
	public List<CartVO> selectDelivery(@Param("username") String username);

	// 관리자가 현재 배송이 몇건인지 조회하는 SELECT
	@Select("SELECT count(*) FROM tbl_cart WHERE p_status = 'DELIVERY' ")
	public int countDelivery();
}
