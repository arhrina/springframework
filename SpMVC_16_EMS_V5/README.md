# EMS Prj
* Email Management System Prj V1
* 2020-01-20

### JPA-Hibernate, MySQL 연동 prj

	<bean id="emsHiber" class="org.apache.commons.dbcp2.BasicDataSource">
		<property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
		<property name="url" value="jdbc:mysql://127.0.0.1:3306/emsDB?serverTimezone=Asia/Seoul&useSSL=false"/>
		<property name="username" value="ems"/>
		<property name="password" value="ems"/>
	</bean>
	
* driverClassName : com.mysql.cj.jdbc.Driver를 사용한다
* url : 기본연결주소에 쿼리값을 추가한다. 서버시간 기본값이 다르게 설정되어 있으므로 serverTimezone을 Asia/Seoul로 설정해야한다
* 5.x에서는 SSL연결을 하지 않는 &useSSL=false 옵션을 사용해야하며 8.x 이상에서는 오류가 발생하니 추가하지 않고 사용한다

### NAVER ID로 로그인 구현

1. naver에 login창을 요청
	- server에 state라는 특별한 key를 생성하고 그 값을 같이 보내야한다
2. 네이버는 로그인창을 redirect해주고 네이버가 보내준 로그인창에 로그인을 수행하고 정상으로 로그인이 되면
3. OAuth20 규격의 token(bus ticket)을 보내주는데 이 토큰을 이용(약간 까다로움)해서 네이버에 회원정보들을 요청할 수 있다