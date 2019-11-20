# PRJ : 학생정보

## base-package : com.biz.student
## pattern : Spring MVC

* PRJ 시작시 설정해줘야할 것들

* pom.xml의 dependency 수정
spring version 5.1.11 변경
lombok 추가
log를 logback으로 설정

* pom.xml의 java version 수정
1.6을 1.8로

* web.xml에 한글인코딩 filter 설정

* homecontroller.java 클래스파일을 com.biz.student.controller로 이동

* servlet-context.xml 수정
conponent-scan의 base-package를 com.biz.student.controller로 변경

* home.jsp의 상단에 page 설정