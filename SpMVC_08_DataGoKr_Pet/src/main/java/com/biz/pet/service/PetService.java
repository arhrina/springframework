package com.biz.pet.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.biz.pet.config.DataGoConfig;
import com.biz.pet.domain.GoPetVO;
import com.biz.pet.domain.pet_rest.RestVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PetService {
	private final String go_pet_url = "http://openapi.jeonju.go.kr/rest/dongmulhospitalservice";

	public String getQueryHeader() {
		String queryString = go_pet_url;
		queryString += "/getDongMulHospital";

		queryString += "?ServiceKey=" + DataGoConfig.DATA_GO_AUTH;
		queryString += "&pageNo=1";
		queryString += "&numOfRows=100";
		return queryString;
	}

	public List<GoPetVO> getRestVOList(String s_cat, String s_text) {
		String queryString = getQueryHeader();
		try {
			if(s_cat.equalsIgnoreCase("ADDR")) {
				queryString += "&address=" + URLEncoder.encode(s_text, "UTF-8");
			}
			else {
			queryString += "&dongName=" + URLEncoder.encode(s_text, "UTF-8");
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// httpRequest Header 설정하기
		HttpHeaders header = new HttpHeaders();

		// JSON으로 수신
		// header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));

		// XML로 수신
		header.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", header);
		RestTemplate restTemp = new RestTemplate(); // spring 3.2에서 도입된 새로운 HttpConnection의 추상화된 객체
		URI restURI = null;
		// ResponseEntity<String> restList = null;
		ResponseEntity<RestVO> restList = null;
		try {
			restURI = new URI(queryString);
			restList = restTemp.exchange(restURI, HttpMethod.GET, entity, RestVO.class);
			RestVO rVO = (RestVO) restList.getBody();
			List<GoPetVO> goPetList = rVO.body.data.list;
			return goPetList;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug(restList.toString());
		// URI를 넣어서 http GET방식으로 String.class 자료형을 받아옴
		// RestTemplate null을 가지면 reponse header에 default로 json 입력
		return null;
	}
/*
	public List<GoPetVO> getData(String s_text) {
		String queryString = getRestString(s_text);

		// 이하 코드가 RestTemplate에 포함되어 있다
		JsonElement jElement = JsonParser.parseString(queryString);
		JsonObject oBody = (JsonObject) jElement.getAsJsonObject().get("body");
		JsonObject oTotal = (JsonObject) oBody.getAsJsonObject().get("totalCount");
		if(Integer.valueOf(oTotal.toString()) < 1 ){
			return null;
		}
		JsonObject oData = (JsonObject) oBody.getAsJsonObject().get("data");
		JsonArray oList = null;
		List<GoPetVO> petList = null;
		Gson gson = new Gson();
		
		try {
			oList = (JsonArray) oData.getAsJsonObject().get("list");
			// JsonArray를 List로 변환
			TypeToken<List<GoPetVO>> typeListToken = new TypeToken<List<GoPetVO>>() {
			};
			petList = gson.fromJson(oList, typeListToken.getType());
			return petList;
			// 정상적으로 List가 모두 생성되었을 경우. 전체 데이터의 List를 return
		} catch (ClassCastException e) {
			// TODO: ClassCastException이 발생시 1개짜리 리스트를 만들어서 return
			log.debug("데이터가 1개뿐");
			JsonObject petObj = (JsonObject) oData.getAsJsonObject().get("list");
			TypeToken<GoPetVO> typeToken = new TypeToken<GoPetVO>() {
			};
		
		
		// List를 추출해 JsonArray로 변환하는 과정에서 ClassCastException이 발생하면(데이터가 1개뿐인 경우)
		
			petList = new ArrayList<GoPetVO>();
			petList.add(gson.fromJson(petObj, typeToken.getType()));
			return petList;
		}
	}
	*/
}
