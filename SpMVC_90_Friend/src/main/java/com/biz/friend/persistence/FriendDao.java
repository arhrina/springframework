package com.biz.friend.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.biz.friend.domain.FriendVO;

public interface FriendDao {
	
	@Select("SELECT * FROM tbl_friend")
	public List<FriendVO> selectAll();
}
