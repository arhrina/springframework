package com.biz.ems.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biz.ems.domain.EmailVO;
import com.biz.ems.service.MybatisService;

import lombok.RequiredArgsConstructor;

@RequestMapping(value="/mybatis_rest")
@RequiredArgsConstructor
@RestController
public class MybatisRestController {

	private final MybatisService myService;
	
	public List<EmailVO> list(){
		return myService.selectAll();
	}
}
