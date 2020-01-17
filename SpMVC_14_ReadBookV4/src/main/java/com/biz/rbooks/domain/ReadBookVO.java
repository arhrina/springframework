package com.biz.rbooks.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * mybatis-context.xml에서 typeAliasPackage를 설정해두면 자동으로 package를 읽어서 VO 파일을 준비하는데
 * 이 때 사용할 이름은 클래스의 첫글자만 소문자로 바꾸는 방식으로 설정한다
 * 
 * 자동으로 설정되는 alias를 임의로 변경하고자 할 때 사용하는 annotation
 */
@Alias("rBookVO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class ReadBookVO {
	private long rb_seq;//	number
	private String rb_bcode;//	varchar2(20 byte)
	
	private String rb_bname; // EQ join으로 가져오는 자료값을 담을 필드
	
	private String rb_date;//	varchar2(10 byte)
	private String rb_stime;//	varchar2(10 byte)
	private float rb_rtime;//	number(10,3)
	private String rb_subject;//	nvarchar2(20 char)
	private String rb_text;//	nvarchar2(400 char)
	private int rb_star;//	number
}
