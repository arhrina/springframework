package com.biz.friend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.friend.domain.FriendVO;
import com.biz.friend.persistence.FriendDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FriendService {
	
	private final FriendDao fDao;
	
	public List<FriendVO> selectAllList() {
		return fDao.selectAll();
	}
}
