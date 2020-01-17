package com.biz.rbooks.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
/*
 * tomcat이 작동하면서 제일 먼저 호출되는 abstractAnnotationConfigDispatcherServletInitializer와 함께 호출되게 만들 클래스
 * web.xml을 대신함
 */
public class AppConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	/*
	  	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/spring/root-context.xml</param-value>
		</context-param>
	 */
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {RootConfig.class}; // 필요는 없지만 안만들어주면 에러발생
	}

	@Override
	/*
	  	<servlet>
		<servlet-name>appServlet</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/spring/appServlet/*-context.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
		</servlet>
	 */
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
//		Class[] servlet = new Class[] {WebConfig.class};
//		return servlet;
		return new Class[] {WebServletConfig.class, MybatisConfig.class};
	}

	@Override
	/*
	  	<servlet-mapping>
		<servlet-name>appServlet</servlet-name>
		<url-pattern>/</url-pattern>
		</servlet-mapping>
	 */
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
//		String[] mapping = new String[] {"/", "*.do"}; // 2개의 문자열로 mapping을 선언url-pattern이 /*.do와 같다
		return new String[] {"/", "*.do"};
	}

}
