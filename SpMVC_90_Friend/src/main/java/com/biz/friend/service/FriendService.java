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
	
	public FriendVO findById(long f_id){
		return fDao.findById(f_id);
	}
	
	public List<FriendVO> findByName(String name){
		return fDao.findByName(name);
	}
	
	public List<FriendVO> findByTel(String tel){
		return fDao.findByPhoneNumber(tel);
	}
	
	public int insert(FriendVO fVO) {
		return fDao.insert(fVO);
	}

	public int delete(long f_id) {
		// TODO Auto-generated method stub
		return fDao.delete(f_id);
	}

	public int update(FriendVO fVO) {
		// TODO Auto-generated method stub
		return fDao.update(fVO);
	}
	
}
