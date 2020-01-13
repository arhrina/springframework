package com.biz.rbooks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class BookVO {
	private String b_code;//	varchar2(20 byte) 도서코드
	private String b_name;//	nvarchar2(125 char) 도서명
	private String b_author;//	nvarchar2(125 char) 저자
	private String b_comp;//	nvarchar2(125 char) 출판사
	private String b_year;//	varchar2(10 byte) 구입일자
	private int b_page;// number 책 페이지수
	private int b_iprice;//	number 구입가격
}
