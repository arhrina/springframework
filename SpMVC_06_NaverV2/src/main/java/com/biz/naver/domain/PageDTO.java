package com.biz.naver.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * pagination의 여러가지 설정값을 저장하고 설정값을 변경하여 쉽게 페이지리스트를 그릴 수 있는 정보를 저장할 컬럼
 * 
 * 전체 데이터의 개수 : 페이지수 계산
 * 현재 페이지에 보여질 리스트 개수
 * 현재 페이지 하단에 보여질 페이지 개수
 * 
 * 보여지는 페이지의 끝번호 : 계산해서 나오는 값(현재 페이지가 8에 있고 5개씩 보여진다고 하면 10)
 * 보여지는 페이지의 첫번호 : 계산해서 나오는 값(현재 페이지가 8에 있고 5개씩 보여진다고 하면 6)
 * 
 * 처음페이지 : 처음으로 가기를 클릭했을 때 보여질 페이지 번호(보통 1)
 * 마지막페이지 : 끝으로 가기를 클릭했을 때 보여질 페이지 번호(전체 페이지 개수의 마지막으로)
 * 
 * 이전페이지
 * 이후페이지
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PageDTO {
	private long totalCount; // 전체 데이터 개수
	
	private long listPerPage; // 한 페이지에 보여질 데이터 개수
	private long pageCount; // 현재 페이지 하단에 보여질 페이지 번호 리스트 개수
	
	private long firstPageNo; // 전체 데이터에 첫 페이지번호(일반적으로 1)
	private long finalPageNo; // 전체 데이터에 마지막 페이지번호(계산결과 필요. 10개씩 자료가 357개라면 36페이지)
	
	private long prePageNo; // 이전페이지
	private long startPage; // 보여지는 리스트의 시작페이지 번호
	
	private long nextPageNo; // 다음페이지
	private long endPageNo; // 보여지는 리스트의 끝번호
	
	private long currentPageNo; // 현재 선택된 페이지 번호
}
