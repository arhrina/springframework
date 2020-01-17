## Java Config 방식 SpringMVC prj
### 2020-01-17

* web.xml을 대신할 ProjectInit.java 클래스를 생성
- AbstractAnnotationConfigDispatcherServletInitializer를 상속받기

* root-context.xml을 대신할 RootConfig.java 클래스를 생성
- @Configuration을 클래스에 지정
- 메서드는 없는 상태

* servlet-context.xml을 대신할 WebServletConfig.java 클래스를 생성
- @Configuration을 클래스에 지정
- @EnableWebMvc를 클래스에 지정
- @ComponentScan을 클래스에 지정하고 컨트롤러와 서비스 패키지를 설정

- addResourceHandlers 메서드를 구현하는 코드 추가
- InternalResourceViewResolver 메서드를 생성하고 매핑을 설정하는 코드 추가 