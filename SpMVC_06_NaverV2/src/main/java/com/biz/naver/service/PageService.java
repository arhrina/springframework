package com.biz.naver.service;

import org.springframework.stereotype.Service;

import com.biz.naver.domain.PageDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PageService {

	private long listPerPage = 10; // 한번에 보여줄 리스트 개수
	private long pageCount = 5; // 한번에 보여줄 페이지 개수
	
	public void setListPerPage(long listPerPage) { // 필요시 외부에서 리스트당 보여지는 페이지수 변경하는 메서드
		this.listPerPage = listPerPage;
	}
	
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	// 최소한의 조건으로 페이지를 계산하기 위한 메서드
	// 전체 데이터개수와 현재 선택된 페이지번호만 매개변수로 받아서 페이지 정보를 생성
	public PageDTO makePagination(long totalCount, long currentPageNo) {
		if(totalCount < 1) return null; // 데이터가 없으면 메서드 중단
		
		// 전체데이터 + 보여질 리스트 -1을 보여질 리스트개수로 나눔
		long finalPageNo = (totalCount + (listPerPage - 1)) / listPerPage;
		// naver는 페이지를 검색할 때 1000page가 넘어가면 오류발생
		// finalPageNo = finalPageNo > 1000 ? 1000 : finalPageNo;
		// if(finalPageNo > 1000) finalPageNo = 1000;를 3항연산자로
		
		// 현재 보고 있는 페이지 currentPageNo가 마지막 페이지보다 크면
		if(currentPageNo > finalPageNo) currentPageNo = finalPageNo;
		
		// 현재 보고 있는 페이지번호가 1보다 작으면
		if(currentPageNo < 1) currentPageNo = 1;
		
		// 이전, 이후 계산하기 위해 현재 페이지번호가 첫페이지인가, 마지막페이지인가를 검사해서 flag를 set
		boolean isNowFirst = currentPageNo == 1; // bYes = 3 == 3인가?
		boolean isNowFinal = currentPageNo == finalPageNo;
		
		// 하단에 리스트로 보여질 페이지 계산
		// cur : 3이면 1~5, cur 10이면 8~12까지 이런식으로 보여주는 계산
		long startPageNo = ((currentPageNo - 1) / this.pageCount) * this.pageCount + 1;
		long endPageNo = startPageNo + this.pageCount - 1;
		if(endPageNo > finalPageNo) endPageNo = finalPageNo;
		
		PageDTO pageDTO = PageDTO.builder()
				.totalCount(totalCount)
				.pageCount(this.pageCount)
				.listPerPage(this.listPerPage)
				.firstPageNo(1)
				.finalPageNo(finalPageNo)
				.startPage(startPageNo)
				.endPageNo(endPageNo)
				.currentPageNo(currentPageNo)
				.build();
		if(isNowFirst) pageDTO.setPrePageNo(1);
		else pageDTO.setPrePageNo((currentPageNo - 1) < 1 ? 1 : currentPageNo -1);
		
		if(isNowFinal) pageDTO.setNextPageNo(finalPageNo);
		else pageDTO.setNextPageNo((currentPageNo + 1) > finalPageNo ? finalPageNo : currentPageNo + 1);
		return pageDTO;
	}
}
