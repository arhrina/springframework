package com.biz.naver.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.naver.config.NaverConfig;
import com.biz.naver.domain.PageDTO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class NaverServiceV3 {
	private final String naver_news_url = "https://openapi.naver.com/v1/search/news.json";
	private final String naver_book_url = "https://openapi.naver.com/v1/search/book.json";
	private final String naver_movie_url = "https://openapi.naver.com/v1/search/movie.json";
	
	@Autowired
	PageService pService;
	
	public JSONArray getNaver(String cat, String search, long currentPageNo) throws IOException, ParseException {
		// 전체 데이터 개수 계산
		String totalQuery = this.queryNaver(cat, search); // 1~10page까지 query
		String totalString = this.getNaverString(totalQuery);
		JSONObject totalJObject = this.strToJson(totalString);
		String strTotal = totalJObject.get("total").toString(); // JSONObject에서 key가 total인 항목을 찾아서 값을 문자열로 추출
		long totalCount = Long.valueOf(strTotal);
		PageDTO pageDTO = pService.makePagination(totalCount, currentPageNo);
		
		log.debug("전체개수 : " + totalCount);
		
		currentPageNo *= 10;
		String queryString = queryNaver(cat, search, currentPageNo, pageDTO.getListPerPage());
		String resString = getNaverString(queryString);
		JSONObject resObject = strToJson(resString);
		JSONArray resArray = getArray(resObject, "items");
		return resArray;
	}
	
	// 카테고리, search, start, display를 매개변수로 받아서 start부터 display 개수만큼 데이터를 가져오기 위한 문자열 생성
	public String queryNaver(String cat, String search, long start, long display) throws UnsupportedEncodingException {
		String queryString = URLEncoder.encode(search, "UTF-8");
		queryString = this.queryURL(cat) + "?query=" + queryString; // https:........../?query=a
		// start와 display를 query에 포함시키면, start부터 display 개수만큼 데이터를 보내라
		queryString += "&start=" + start;
		queryString += "&display=" + display;
		return queryString;
	}
	
	// queryNaver에서 생성한 queryString문자열을 매개변수로 naver에 전송하고 response된 문자열을 return
	public String getNaverString(String queryString) throws IOException {
		URL url = new URL(queryString);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("GET");
		httpConn.setRequestProperty("X-Naver-Client-Id", NaverConfig.NaverClientId); // 대소문자 정확히
		httpConn.setRequestProperty("X-Naver-Client-Secret", NaverConfig.NaverClientSecret); // http header에 정보싣기
		int resCode = httpConn.getResponseCode();
		BufferedReader br = null;
		if(resCode == 200) { // 네이버가 정상요청을 받아 데이터를 준비시
			InputStreamReader is = new InputStreamReader(httpConn.getInputStream());
			br = new BufferedReader(is);
		}
		else // 오류가 발생했을 때, 에러메시지를 에러스트림을 통해 받기
		{
			InputStreamReader is = new InputStreamReader(httpConn.getErrorStream());
			br = new BufferedReader(is);
		}
	
		/*
		 * String은 내부에서 주소를 가지고 해당주소에 값을 가지고 있는데, 새로운 값을 넣으면 기존 값은 놔둔채로 새로운 저장공간을 할당한다
		 * 메모리 낭비가 심하므로 StringBuffer를 사용하며 java 1.7 이상에서는 해당 동작이 발생하면 StringBuffer로 내부에서 교체된다
		 * 
		 * 단, new String을 계속 반복하지 않아야한다
		 */
		StringBuffer resString = new StringBuffer(); // 리스트처럼 동작
		String reader = "";
		while(true) {
			reader = br.readLine();
			if(reader == null) break;
			resString.append(reader);
		}
		if(resCode == 200) {
			return resString.toString();
		}
		else
		{ // 에러가 발생했으면 디버깅을 위한 코드
			log.debug("네이버오류: " + resString.toString());
		}
		return null;
	}
	
	// 문자열을 JSONObject로 변환
	public JSONObject strToJson(String jsonString) throws ParseException {
		JSONParser jParser = new JSONParser();
		JSONObject jObject = (JSONObject) jParser.parse(jsonString);
		return jObject;
	}
	
	// JSONObject로부터 Naver의 items만 추출하여 JSONArray로 변환
	// naver로 받은 데이터에서 items 항목을 추출하여 실제 데이터들을 Array로 만드는 메서드
	public JSONArray getArray(JSONObject jObject, String keyString) {
		return (JSONArray) jObject.get(keyString);
	}
	
	public String queryURL(String cat) {
		String queryURL = naver_news_url;
		if(cat.equalsIgnoreCase("NEWS")) { // 카테고리에 따라 URL을 가져와서 return
			queryURL = naver_news_url;
		}
		else if(cat.equalsIgnoreCase("BOOK")) {
			queryURL = naver_book_url;
		}
		else if(cat.equalsIgnoreCase("MOVIE")) {
			queryURL = naver_movie_url;
		}
		return queryURL;
	}
	
	// 무조건 1번부터 10개씩 데이터를 받아온다
	public String queryNaver(String cat, String search) throws UnsupportedEncodingException {
		String queryString = URLEncoder.encode(search, "UTF-8");
		queryString = this.queryURL(cat) + "?query=" + queryString; // https:........../?query=a
		return queryString;
	}
}
