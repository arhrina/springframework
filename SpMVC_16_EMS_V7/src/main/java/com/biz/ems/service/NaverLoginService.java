package com.biz.ems.service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.SecureRandom;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.biz.ems.config.Naver;
import com.biz.ems.domain.NaverMember;
import com.biz.ems.domain.NaverMemberResponse;
import com.biz.ems.domain.NaverReturnAuth;
import com.biz.ems.domain.NaverTokenVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NaverLoginService {


	private final String clientId = "hedAM2ytv1O_uwXrEQ1t";
	private final String clientSec = "MQGuSBJqRS";
	
	private final String loginAPI_URL = "https://nid.naver.com/oauth2.0/authorize"; // 요청할 URL
	private final String tokenAPI_URL = "https://nid.naver.com/oauth2.0/token"; // 요청할 URL
	private final String naverMemberAPI_URL = "https://openapi.naver.com/v1/nid/me";
	
	private final String callbackLocalURL = "http://localhost:8080/ems/naver/ok";
	private final String callbackURL = "https://callor.com:12600/ems_hyoukim/member/naver/ok"; // 네이버에서 이 주소로 response

	public String oAuthLoginGet() { // 네이버에 req하는 문자열을 생성. 인증문자열을 URL로 생성
		
		String redirectURI = null;
		try {
			redirectURI 
			= URLEncoder.encode(callbackURL,"UTF-8"); // get방식은 ? 뒤에 붙으므로 URL Encoding을 반드시 해줘야한다
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SecureRandom random = new SecureRandom();
		
		// 최대값 130bit 크기 임의의 정수를 생성하여 문자열로 만들어
		String stateKey = new BigInteger(130,random).toString();
		log.debug("STATE KEY : " + stateKey);
		
		String apiURL = loginAPI_URL ;
		apiURL += "?client_id=" + this.clientId;
		apiURL += "&response_type=code";
		apiURL += "&redirect_uri="+redirectURI;
		apiURL += "&state=" + stateKey;
		
		log.debug("URL : " + apiURL);
		return apiURL; // controller에 apiURL을 넘겨주기
	}
	/*
	 * 네이버에 정보요구시 사용할 토큰을 요청
	 * 토큰을 요청하기 위해 GET/POST 메서드를 사용할 수 있는데
	 * 여기선 POST를 사용. 보안적인 면에서 유리
	 */
	public NaverTokenVO oAuthAccessGetToken(NaverReturnAuth naverOK) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Naver-Client-Id", clientId);
		headers.set("X-Naver-Client-Secret", clientSec);
		
		// Map interface를 상속받아 작성된 spring framework 전용 Map interface
		// http protocol과 관련된 곳에서 기본 Map 대신 사용
		// http protocol과 관련된 내장 메서드가 포함
		// 선언은 MultiValueMap으로 하고 실제 생성은 LinkedMlutiValueMap으로 한다
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		
		params.add(Naver.TOKEN.grant_type, Naver.GRANT_TYPE.authorization_code);
		params.add(Naver.TOKEN.client_id, clientId);
		params.add(Naver.TOKEN.client_secret, clientSec);
		params.add(Naver.TOKEN.code, naverOK.getCode());
		params.add(Naver.TOKEN.state, naverOK.getState());
		
		// headers에 담긴 정보와 params에 담긴 정보를 HttpEntity 데이터로 변환. Http 프로토콜에 Body부분에 자료를 실어보냄
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String,String>>(params, headers);
		
		// http 전송을 위해 URI 객체 생성
		URI restURI = null;
		try {
			restURI = new URI(tokenAPI_URL);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// RestTemplate를 사용해 네이버에 토큰을 요청
		RestTemplate restTemp = new RestTemplate();
		ResponseEntity<NaverTokenVO> result = null;
		
		// restURI에 POST로 request값을 보내고 NaverTokenVO.class로 데이터를 받자
		result = restTemp.exchange(restURI, HttpMethod.POST, request, NaverTokenVO.class);
		
		log.debug(result.getBody().toString());
		return result.getBody();
	}
	
	public NaverMember getNaverMemberInfo(NaverTokenVO tokenVO) { // 토큰을 보내 VO에 맞춰 자료값 받기
		String token = tokenVO.getAccess_token();
		String header = "bearer " + token;
		
		// header 문자열을 http header에 실어 GET방식으로 요청
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", header);
		
		HttpEntity<String> request = new HttpEntity<String>("parameter", headers); // http header에 들어가는 공식 이름이 parameter
		ResponseEntity<NaverMemberResponse> result;
		RestTemplate restTemp = new RestTemplate();
		
		result = restTemp.exchange(naverMemberAPI_URL, HttpMethod.GET, request, NaverMemberResponse.class);
		
		NaverMember member = result.getBody().response;
		return member;
	}
}
