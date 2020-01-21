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