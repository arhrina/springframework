package com.biz.student.service;

import java.util.ArrayList;
import java.util.List;

import com.biz.student.domain.StudentDTO;

public class StudentService {
	
	private String st_name;
	private String st_dept;
	
	public StudentService() {
		// TODO
	}
	
	public StudentService(String name, String dept) {
		st_name = name;
		st_dept = dept;
	}
	
	public void viewStudent() {
		System.out.println(st_name);
		System.out.println(st_dept);
	}
	
	public List<StudentDTO> stdList(){
		List<StudentDTO> stdList = new ArrayList<StudentDTO>();
		StudentDTO stDTO = new StudentDTO();
		stDTO.setSt_num("0001");
		stDTO.setSt_name("Hong");
		stDTO.setSt_dept("Computer");
		stDTO.setSt_grade(3);
		stDTO.setSt_tel("010-111-1234");
		stdList.add(stDTO);
		
		stDTO = new StudentDTO();
		stDTO.setSt_num("0002");
		stDTO.setSt_name("LeeMong");
		stDTO.setSt_dept("Computer");
		stDTO.setSt_grade(2);
		stDTO.setSt_tel("010-111-2234");
		stdList.add(stDTO);
		
		stDTO = new StudentDTO();
		stDTO.setSt_num("0003");
		stDTO.setSt_name("Seong");
		stDTO.setSt_dept("Computer");
		stDTO.setSt_grade(1);
		stDTO.setSt_tel("010-111-2234");
		stdList.add(stDTO);
		
		return stdList;
	}
}
