package com.biz.todo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biz.todo.domain.ToDoList;

public interface ToDoListDao {
	
	public List<ToDoList> selectAll();
	public int insert(ToDoList todoList);
	// public int complete(long longSeq);
	// 매개변수가 1개면 Mapper에 이름이 달라도 무조건 매핑이 된다. 2개의 경우는 문제발생. @Param으로 변수이름을 정해줘야함
	public int complete( // 토글을 위한 2개 변수
			@Param("tdSeq") long longSeq,
			@Param("tdComplete") String tdComplete);
	public int alarm(@Param("tdSeq") long tdSeq, @Param("tdAlarm") String strAlarm);

}
