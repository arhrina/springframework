package com.biz.gallery.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

/*
 * REQ 가로채기를 실시하도록
 */
@Slf4j
public class AjaxInterceptor extends HandlerInterceptorAdapter {

	/*
	 * Client로부터 REQ를 받으면 Dispatcher에서 Controller로 가는 도중 가로채기를 수행하는 method 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception { // servletReq는 모든 정보를 다 가지고 있다
		// TODO Auto-generated method stub
		
		// login 정보 확인. dispatcher가 안넣어주므로 req로부터 자료를 빼와야
		HttpSession httpSession = request.getSession();

		// MEMBER Session을 확인하기 위해서 Attribute를 추출해서 obj 객체에 담기
		Object memberVO = httpSession.getAttribute("MEMBER");
		
		// obj 객체가 null인지 확인. null이면 MEMBER Session이 없다는 의미. 없으면 로그인이 안된 상태이므로 로그인창으로
		if(memberVO == null) {
			response.setStatus(403);
			// response.setStatus(HttpServletResponse.SC_FORBIDDEN); 이것이 403
			return false;
		}
		return super.preHandle(request, response, handler); // = return true. 컨트롤러에게 전달
	}
}
