package com.biz.shop.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.biz.shop.domain.CustomUserDetails;
/*
 * support 메서드가 true를 리턴하면
 * 정상로그인된 사용자 정보가 토큰 형태로 default-target-url에 controller
 * requestMapping value가 동일한 메서드로 넘어간다
 * 
 * 
 * authenticate 메서드에서 필요한 사용자 정보를 만들거나 가공할 수 있다
 */
public class CustomAuthProvider implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username = (String) authentication.getPrincipal(); // username 추출
		String password = (String) authentication.getCredentials(); // password 추출
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		roles.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
		
		/*
		 * 해당 사용자의 Detail한 정보가 DB에 있다면
		 * 해당 정보를 DB에서 조회하여 CustomUserDetails 클래스에 세팅하여
		 * Controller로 전송할 수 있다
		 */
		CustomUserDetails cUserDetails = new CustomUserDetails();
		cUserDetails.setNick_name("HONG");
		cUserDetails.setTel("010-111-1111");
		cUserDetails.setAddr("서울");
		
		UsernamePasswordAuthenticationToken token =
				new UsernamePasswordAuthenticationToken(username, //principal,
						password,// credentials,
						roles); //authorities)
		token.setDetails(cUserDetails);
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
