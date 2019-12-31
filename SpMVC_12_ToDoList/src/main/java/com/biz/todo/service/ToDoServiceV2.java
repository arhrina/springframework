package com.biz.todo.service;

import org.springframework.stereotype.Service;

@Service("toDoServiceV2")
public class ToDoServiceV2 extends ToDoServiceV1 {
	
	@Override
	public int complete(String strSeq, String strComplete) {
		long tdSeq = Long.valueOf(strSeq);
		strComplete = strComplete.equalsIgnoreCase("y") ? "N" : "Y"; // Y면 N으로 아니면 Y로 토글
		return toDao.complete(tdSeq, strComplete);
	}

	@Override
	public int alarm(String strSeq, String strAlarm) {
		long tdSeq = Long.valueOf(strSeq);
		strAlarm = strAlarm.equalsIgnoreCase("y") ? "N" : "Y"; // Y면 N으로 아니면 Y로 토글
		return toDao.alarm(tdSeq, strAlarm);
	}
	
	
	
}
