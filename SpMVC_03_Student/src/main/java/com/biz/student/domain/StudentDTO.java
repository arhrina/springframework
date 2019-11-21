package com.biz.student.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * DB 설계를 수행한 후 설계된 TABLE과 연계되는 DTO(VO) 설계
 * 
 * 객체지향에서 DTO(VO)를 설계하는 단계 : 추상화
 * 
 * 실제 상황에서 사용되는 데이터를 특징적인 부분들만 추려서 특정한 클래스로 설계
 * 
 * 학번, 이름, 학과, 학년, 전화번호, 주소, 담당교수
 * prefix를 붙여서 만든다
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class StudentDTO {
	private String st_num; // 2019010001
	private String st_name;
	private String st_dept;
	private int st_grade;
	private String st_tel;
	private String st_addr;
	private String st_pro;
}
