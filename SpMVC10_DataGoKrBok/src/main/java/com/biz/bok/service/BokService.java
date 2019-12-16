package com.biz.bok.service;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.biz.bok.config.DataGoConfig;
import com.biz.bok.domain.BokSearchDTO;

@Service
public class BokService {
	private final String bok_url = "http://www.bokjiro.go.kr/openapi/rest/gvmtWelSvc";
	private final String bok_list_url = "/NationalWelfarelist";
	private final String bok_detail_url = "/NationalWelfaredetailed";

	public String getQueryHeader(BokSearchDTO bokDTO) {
		String queryString = bok_url;
		/*
		 * if (bokDTO.getCallTp().equalsIgnoreCase("L")) { queryString += bok_list_url;
		 * } else queryString += bok_detail_url;
		 */
		queryString += "?crtiKey=" + DataGoConfig.DATA_GO_AUTH;// 인증키
		queryString += "&callTp=" + bokDTO.getCallTp();// 페이지타입
		queryString += "&pageNo=" + bokDTO.getPageNo();// 페이지시작위치
		queryString += "&numOfRows=" + bokDTO.getNumOfRows();// 출력건수
		queryString += "&srchKeyCode=" + bokDTO.getSrchKeyCode();// 검색분류
		String searchWrd = "";
		try {
			searchWrd = URLEncoder.encode(bokDTO.getSearchWrd(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		queryString += "&searchWrd=" + searchWrd;// 검색어
		queryString += "&lifeArray=" + bokDTO.getLifeArray();// 생애주기
		queryString += "&charTrgterArray=" + bokDTO.getCharTrgterArray();// 대상특성
		queryString += "&obstKiArray=" + bokDTO.getObstKiArray();// 장애유형
		queryString += "&obstAbtArray=" + bokDTO.getObstAbtArray();// 장애정도
		queryString += "&trgterIndvdlArray=" + bokDTO.getTrgterIndvdlArray();// 가구유형
		queryString += "&desireArray=" + bokDTO.getDesireArray();// 욕구
		return queryString;
	}

	public String getRestResult(BokSearchDTO bokDTO) {
		ResponseEntity<String> result = null;
		URI restURI = null;
		RestTemplate restTemp = new RestTemplate(); // HTTP Connection의 새로운 기능
		// ParameterizedType<String> paramType;
		try {
			restURI = new URI(getQueryHeader(bokDTO));
			result = restTemp.exchange(restURI, HttpMethod.GET, null, String.class);
			return result.getBody();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public BokSearchDTO getDefaultSearch() { // 검색에 필요한 기본변수 세팅
		return BokSearchDTO.builder()
				.callTp("L")
				.pageNo("1")
				.numOfRows("10")
				.build();
	}
}