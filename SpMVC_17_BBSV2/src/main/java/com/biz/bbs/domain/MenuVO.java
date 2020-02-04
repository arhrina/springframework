package com.biz.bbs.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class MenuVO {
	private String menu_id;//	varchar(3)	not null	primary key
	private String menu_p_id;//	varchar(3)		
	private String menu_title;//	varchar(10)	not null	
	private String menu_href;//	varchar(125)
	
	/*
	 * drop down을 구현하기 위해 자기 자신을 리스트로 설정
	 */
	List<MenuVO> drop_menus;
}
