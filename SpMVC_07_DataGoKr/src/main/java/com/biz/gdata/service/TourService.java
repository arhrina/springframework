package com.biz.gdata.service;

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
import org.springframework.stereotype.Service;

import com.biz.gdata.config.DataGoConfig;
@Service
public class TourService {

	// 문자열 생성
	public String getQueryString() throws UnsupportedEncodingException {
		String queryString = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode";
		queryString += "?ServiceKey=" + DataGoConfig.goDataAuth;
		queryString += "&MobileApp=" + URLEncoder.encode(DataGoConfig.MY_APP_NAME, "UTF-8");
		queryString += "&_type=json";
		queryString += "&MobileOS=ETC";

		queryString += String.format("&numOfRows=%d", 10);
		queryString += String.format("&pageNo=%d", 1);

		return queryString;
	}
	
	public JSONArray getData() throws ParseException, IOException {
		JSONParser jParser = new JSONParser();
		JSONObject jObject = (JSONObject) jParser.parse(this.getDataString());
		JSONObject oBody = (JSONObject) jObject.get("body");
		JSONObject oItems = (JSONObject) oBody.get("items");
		JSONArray oItem = (JSONArray) oItems.get("item");
		return oItem;
	}

	public String getDataString() throws IOException {
		URL url = new URL(this.getQueryString());
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("GET");
		int resCode = httpConn.getResponseCode();
		BufferedReader br = null;
		if (resCode == 200) { // 정상작동하면
			InputStreamReader is = new InputStreamReader(httpConn.getInputStream());
			br = new BufferedReader(is);
		} else { // 에러가 뜨면. 예외처리
			InputStreamReader is = new InputStreamReader(httpConn.getInputStream());
			br = new BufferedReader(is);
		}
		String retString = "";
		String reader = "";
		while (true) {
			reader = br.readLine();
			if (reader == null)
				break;
			retString += reader;
		}
		br.close();
		return retString;
	}
}
