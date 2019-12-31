package com.biz.todo.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.todo.domain.ToDoList;
import com.biz.todo.repository.ToDoListDao;

@Service("toDoServiceV1") // 서비스에 이름을 붙이기
public class ToDoServiceV1 implements ToDoService {

	/*
	 * mybatis-context.xml 파일에 다음 bean을 설정해 두면
	 *  
	 * 	<bean class="org.mybatis.spring.mapper.MapperFactoryBean">
	 * 		<property name="mapperInterface">
	 * 			<value>com.biz.todo.repository.ToDoListDao</value>
	 * 		</property>
	 * 		<property name="sqlSessionFactory" ref="sqlSessionFactory"/>
	 * 	</bean>
	 * 
	 * sqlSession을 경유하여 mapper(dao)를 가져오지 않고
	 * 직접 dao를 사용하여 method를 호출할수 있다. 
	 */
	@Autowired
	protected ToDoListDao toDao; // 상속받아서 autowired를 쓰지 않게 하기 위해 생성자로 protected를 사용
	
	@Override
	public List<ToDoList> selectAll() {
		return toDao.selectAll();
	}

	@Override
	public int insert(ToDoList toDoList) {
		
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat st = new SimpleDateFormat("hh:mm:ss");
		
		String strCom = toDoList.getTdComplete();
		if(strCom == null) {
			toDoList.setTdComplete("N");
		}
		
		String strAlarm = toDoList.getTdAlarm();
		if(strAlarm == null) {
			toDoList.setTdAlarm("N");
		}
		
		String curDate = sd.format(date); // 문자열형 날짜 생성
		String curTime = st.format(date); // 문자열형 시간 생성
		
		toDoList.setTdDate(curDate);
		toDoList.setTdTime(curTime);
		int ret = toDao.insert(toDoList);
		return ret;
	
	}

	@Override
	public ToDoList findBySeq(long tdSeq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ToDoList> findBySubject(String tdSubject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(ToDoList toDolist) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long tdSeq) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int complete(String strSeq, String strComplete) {
		return 0;
	}

	@Override
	public int alarm(String strSeq, String strAlarm) {
		// TODO Auto-generated method stub
		
	}

}
