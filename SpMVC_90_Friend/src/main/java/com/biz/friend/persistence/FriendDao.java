package com.biz.friend.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.biz.friend.domain.FriendVO;

public interface FriendDao {
	
	@Select("SELECT * FROM tbl_friend")
	public List<FriendVO> selectAll();
	
	@Select("SELECT * FROM tbl_friend WHERE f_id = #{f_id}")
	public FriendVO findById(long f_id);
	
	@Select("SELECT * FROM tbl_friend WHERE f_name LIKE CONCAT('%', #{fName}, '%') ")
	public List<FriendVO> findByName(String fName);
	
	@Select("SELECT * FROM tbl_friend WHERE f_tel LIKE CONCAT('%', #{fTel}, '%') ")
	public List<FriendVO> findByPhoneNumber(String fTel);
	
	@Insert("INSERT INTO tbl_friend(f_name, f_tel, f_addr, f_hobby, f_relat) VALUES(#{f_name}, #{f_tel}, #{f_addr}, #{f_hobby}, #{f_relat})")
	public int insert(FriendVO fVO);
	
	public int update(FriendVO fVO);
	
	@Delete("DELETE FROM tbl_friend WHERE f_id = #{f_id}")
	public int delete(long f_id);
}
