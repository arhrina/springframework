package com.biz.rbooks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/*
 * xml이 없는 mvc prj의 web.xml을 대신할 클래스. 실제 상황은 servlet-context.xml의 일부기능만 추가
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.biz.rbooks.controller", "com.biz.rbooks.service"})
public class WebServletConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addResourceHandler("/css/**").addResourceLocations("/css/"); // web.xml에 resource mapping
		WebMvcConfigurer.super.addResourceHandlers(registry);
	} // pom.xml에 failOnMissingWebXml을 false로 세팅하고 xml파일들을 삭제했을 때 web.xml을 대신한다.
	
	@Bean
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class); // xml 방식에서는 jsp를 해석하는건 jstlview밖에 할수없다
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

}
