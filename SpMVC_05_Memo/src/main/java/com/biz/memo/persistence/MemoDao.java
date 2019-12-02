package com.biz.memo.persistence;

import java.util.List;

import com.biz.memo.domain.MemoDTO;

public interface MemoDao {
	public List<MemoDTO> selectAll(); // memoDTO를 여러개를 가져와야되므로 return 타입이 List
	public MemoDTO findById(long m_seq); // 하나만 가져오므로 return type DTO
	public List<MemoDTO> findByCategory(String m_cat);
	public List<MemoDTO> findBySearch(MemoDTO memoDTO);
	
	public int insert(MemoDTO memoDTO); // memoDTO에 담겨있는 값들을 insert 해야하므로 매개변수로 설정
	public int update(MemoDTO memoDTO);
	public int delete(long m_seq); // 유일한 seq 값을 참조하여 삭제를 진행하므로 seq값의 자료형으로 매개변수 설정
}
