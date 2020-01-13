package com.biz.rbooks.repository;

import org.apache.ibatis.annotations.Insert;

import com.biz.rbooks.domain.MemberVO;

public interface MemberDao {

	@Insert("INSERT INTO tbl_member(m_id, m_password) VALUES(#{m_id, jdbcType=VARCHAR}, #{m_password, jdbcType=VARCHAR})")
	int insert(MemberVO mVO);

}
