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
public class AuthInterceptor extends HandlerInterceptorAdapter {

	/*
	 * Client로부터 REQ를 받으면 Dispatcher에서 Controller로 가는 도중 가로채기를 수행하는 method 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception { // servletReq는 모든 정보를 다 가지고 있다
		// TODO Auto-generated method stub
		String urlPath = request.getRequestURL().toString();
		String uriPath = request.getRequestURI().toString();
		String msg = String.format("URL : %s\nURI : %s", urlPath, uriPath);
		
		// login 정보 확인. dispatcher가 안넣어주므로 req로부터 자료를 빼와야
		HttpSession httpSession = request.getSession();

		// MEMBER Session을 확인하기 위해서 Attribute를 추출해서 obj 객체에 담기
		Object sessionObj = httpSession.getAttribute("MEMBER");
		
		// obj 객체가 null인지 확인. null이면 MEMBER Session이 없다는 의미. 없으면 로그인이 안된 상태이므로 로그인창으로
		if(sessionObj == null) {
			// context/image/update에서 로그인 path로 redirect를 수행
			// 경로지정이 애매하게 작동중. 초창기 servlet 기술이라 spring과 호환이 잘 되지 않음
			// 현재 경로가 /image/update이기 때문에 ../을 수행하여 context(gallery/)로부터 경로를 시작하게 해준다
			
			// 로그인이 되어있지 않으므로 로그인화면 jsp로 redirect 수행
			response.sendRedirect(request.getContextPath() + "/member/login");
			return false; // 현재 로그인이 안되어있으므로 dispatcher는 더이상 일을 수행하지 말라
		}
		
		
		log.debug("인터셉터 동작확인");
		log.debug(msg);
		return super.preHandle(request, response, handler); // = return true. 컨트롤러에게 전달
	}
}
