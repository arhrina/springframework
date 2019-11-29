package com.biz.iolist.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.iolist.domain.DeptDTO;
import com.biz.iolist.persistence.DeptDao;

@Service
public class DeptService {

	@Autowired
	SqlSession sqlSession;
	
	DeptDao deptDao;
	/*
	 * service에서 deptDao가 필요할 때, 스프링이 자동으로 getDeptDaoMapper 메소드를 호출하여 deptDao를 초기화생성해줌
	 */
	
	@Autowired
	public void getDeptDaoMapper() {
		deptDao = sqlSession.getMapper(DeptDao.class);
	}
	
	public List<DeptDTO> getAllList() {
		// DeptDao deptDao = sqlSession.getMapper(DeptDao.class);
		List<DeptDTO> deptList = deptDao.selectAll();
		return deptList;
	}

	public int insert(DeptDTO deptDTO) {

		// DeptDao deptDao = sqlSession.getMapper(DeptDao.class);
		
		/*
		 * 거래처코드 자동생성을 해서
		 * deptDTO의 d_code에 저장
		 */
		// D0900
		String d_code = deptDao.getDCode();
		
		// 0900 만 추출
		String d_code_num = d_code.substring(1);
		
		//901번 값 생성
		int int_dcode = Integer.valueOf(d_code_num) + 1;
		
		// D 만추출
		d_code = d_code.substring(0,1);
		
		// "D" + "0901"
		d_code += String.format("%04d",int_dcode);
		deptDTO.setD_code(d_code);
		int ret = deptDao.insert(deptDTO);
		return ret;
	
	}

	public DeptDTO findByDCode(String d_code) {
		
		// DeptDao deptDao = sqlSession.getMapper(DeptDao.class);
		DeptDTO dDTO = deptDao.findByDCode(d_code);
		return dDTO;
	
	}

	public int delete(String d_code) {

		// DeptDao deptDao = sqlSession.getMapper(DeptDao.class);
		int ret = deptDao.delete(d_code);
		return ret;
	}

	public int update(DeptDTO deptDTO) {
		
		// DeptDao deptDao = sqlSession.getMapper(DeptDao.class);
		int ret = deptDao.update(deptDTO);
		return ret;
	
	}

	public List<DeptDTO> selectNameSearch(String strText) {
		/*
		 * 매개변수로 전달받은 strText를 거래처코드로 조회하고 값이 있으면(조회되면) 해당정보를 리스트에 담고,
		 * 값이 없으면(조회되지 않으면) 거래처이름에서 strText를 검색해서 리스트에 담는다 
		 */
		List<DeptDTO> deptList;
		DeptDTO dDTO = deptDao.findByDCode(strText);
		if(dDTO != null) {
			deptList = new ArrayList<DeptDTO>();
			deptList.add(dDTO);
		}
		else {
			deptList = deptDao.findByDName(strText);
		}
		return deptList;
	}
}