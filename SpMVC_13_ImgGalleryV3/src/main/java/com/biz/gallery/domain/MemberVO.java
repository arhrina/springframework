package com.biz.gallery.domain;

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
@Builder
@ToString

public class MemberVO {
	//VARCHAR2(125) 
	//nVARCHAR2(125) nullable
	//nVARCHAR2(125)
	//varchar2(125) nullable
	//varchar2(20) nullable
	//varchar2(5)
	private String u_id;
	private String u_nick;
	private String u_name;
	private String u_password;
	private String u_tel;
	private String u_grade;
}
