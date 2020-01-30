package com.biz.ems.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class NaverTokenVO {
	
	// 네이버로부터 발급받은 토큰
	private String access_token;
	
	// (선택사항)네이버에 토큰을 재발급 요청시 받는 토큰
	private String refresh_token;
	
	private String token_type;
	
	// 토큰의 유효기간
	private int expires_in;
	
	// (선택사항)오류가 발생했을 때 에러코드와 에러메시지 	
	private String error;
	private String error_description;
}
