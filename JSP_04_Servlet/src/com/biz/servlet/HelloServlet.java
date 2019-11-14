package com.biz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * HttpServlet 클래스를 상속받아서 생성한 서블릿클래스
 * 사용자가 Web을 통해 request를 보내면 요청을 수신해서 처리할 Application의 대문역할
 * 콘솔에서 main이 있는 클래스와 비슷한 역할
 * 
 * 1. 사용자가 브라우저를 통해 요청을 보낸다
 * 2. 서버컴퓨터의 네트워크를 통해 전송된 데이터를 톰캣이 수신
 * 3. URL의 context를 확인하여 현재 실행되고 있는 프로젝트가 있는지 확인
 * 4. 프로젝트에 작성되어있는 Servlet 클래스들이 있는지 확인
 * 5. Servlet의 @WebServlet에 설정된 path를 scan하여 사용자의 요청을 해당 클래스의
 * 		doGet(), doPost() 등으로 전달
 * 
 * 톰캣은 app이 서버에 의해 실행되면(Run As Server Tomcat)
 * 1. prj에 담겨있는 모든 *.jsp 파일을 내부적으로 *_jsp.java(내부적으로 servlet과 구조가 같다)로
 * 		언어컴파일(translation) 수행
 * 2. *_jsp.java를 *_jsp.class로 컴파일 수행
 * 3. 이 *_jsp.class들을 container에 list로 올려두고 request 대기
 * 
 * -- 이하는 사용자가 생성한 servlet, tomcat이 생성한 *_jsp.class 모두에 해당
 * 4. 만들어진 servlet 클래스가 있으면 모두 컴파일을 수행하고 객체로 선언, 생성해서 container에
 * 		list로 올려둔다
 * 5. 브라우저에서 request가 오면 container list에서 해당 정보를 찾아서 사용자의 요청을 처리 
 * 
 * @WebServlet()은 사용자의 request URL(URI) 중 path라고 하는 부분의 ID를 설정하는 파트
 * WAS(Web Application Server, Service)에서 클래스 이름은 외부로 직접 노출되지 않고,
 * path에 지정된 값을 통해 외부 접근을 허용한다
 * ID값은 현재 프로젝트에서 유일한 값이어야한다
 * 경우에 따라 /main_path/sub_path/child_path 등으로 path를 설정할 수도 있다 
 */
@WebServlet("/hello") // 정확히 대소문자를 구분하는 서버도 있고 구분않는 서버도 있으므로 path는 소문자로 통일
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String st_name = request.getParameter("st_name"); // form에 명명한 이름
		// jsp의 input box(st_name)에 담겨온 문자열을 추출해서 st_name 변수에 담고 보이기
		System.out.println(st_name);
		
		String st_dept = request.getParameter("st_dept");
		String st_grade = request.getParameter("st_grade");

		// 웹에 문자열을 보낼 때 영어 이외 문자의 인코딩 설정
		response.setContentType("text/html;charset=UTF-8");
		
		// 서버에서 웹으로 데이터를 return하는 path open
		/*
		PrintWriter wOut = response.getWriter();
		wOut.println(st_name);
		wOut.close();
		
		*/
		
		// 데이터를 웹 화면에 표시하는 것이 아니고 webcontent/hello.jsp를 열어서 웹으로 전송해라
		// response.sendRedirect("/JSP_04_Servlet/hello.jsp?st_name=" + st_name);
		// 지정해준 jsp파일로 st_name을 날린다
		String sendString = "st_name=" + URLEncoder.encode(st_name, "UTF-8");
		sendString += "&st_dept=" + URLEncoder.encode(st_dept, "UTF-8");
		sendString += "&st_grade=" + URLEncoder.encode(st_grade, "UTF-8");
		// 전송하는 내용이 한글인 경우 UTF-8로 인코딩
		response.sendRedirect("/JSP_04_Servlet/view.jsp?" + sendString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
