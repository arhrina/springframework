package com.biz.shop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Table(name = "tbl_cart", schema = "emsDB")
@Entity
public class CartVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long seq;
	
	@Column(name="username")
	private String username;
	
	@Column(name="p_code")
	private String p_code; // 상품코드

	@Transient // VO에서만 사용. 테이블에 칼럼으로 등록하지 않음
	private String p_name;
	
	@Column
	private int p_oprice; // 판매가격
	
	@Column
	private int p_qty; // 물건개수
	
	@Column(name="p_status", length = 10)
	private String p_status;
	
}
