package com.biz.ems.service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NaverLoginService {

	private final String clientId = "hedAM2ytv1O_uwXrEQ1t";
	private final String loginAPI_URL = "https://nid.naver.com/oauth2.0/authorize";
	private final String callbackLocalURL = "http://localhost:8080/ems/naver/ok";
	private final String callbackURL = "https://callor.com:12600/ems/naver/ok";
	
	public String oAuthLoginGet() {
		
		String redirectURI = "null";
		
		try {
			// 네이버에서 돌려주는 값을 받기 위해 임시로 라이브서버로 콜백하기 위해 주소지정
			redirectURI = URLEncoder.encode("callbackURL", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SecureRandom random = new SecureRandom(); // 자바 지원 클래스
		
		String stateKey = new BigInteger(130, random).toString(); // 최대값 130bit 크기 임의의 정수를 생성해서 문자열로 만들라
		
		log.debug(stateKey);
		
		String getLoginURL = loginAPI_URL;
		getLoginURL += "?client_id=" + clientId;
		getLoginURL += "&redirect_uri=" + redirectURI;
		getLoginURL += "&state=" + stateKey;
		
		log.debug(getLoginURL);
		
		return getLoginURL;
	}
}
