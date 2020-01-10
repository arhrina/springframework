package com.biz.gallery.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

/*
 * REQ 가로채기를 실시하도록. Session 정보가 없으면 BODY에 LOGIN을 담아 같이 보내기
 */
@Slf4j
public class UploadInterceptor extends HandlerInterceptorAdapter {
	
	
	@Override // 보통 preHandle를 가지고 하는 코드는 많지만 postHandle은 별로 없다
	//  pre는 Dispatcher가 Controller로 갈때 인터셉트. post는 요청을 controller로 보내고 view로 가는 단계에서 인터셉트
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HttpSession h = request.getSession(); // 로그인을 확인하는 절차
		Object memberVO = h.getAttribute("MEMBER");
		if(memberVO == null) { // 로그인이 안되어있으면
			request.setAttribute("MODAL", "LOGIN"); // MODAL값에 LOGIN이라는 문자열을 담아서 request를 실행
		}
		super.postHandle(request, response, handler, modelAndView);
	}
}
