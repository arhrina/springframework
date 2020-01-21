package com.biz.ems.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biz.ems.domain.EmailVO;

@Repository
public interface EmailDao extends CrudRepository<EmailVO, Long> { // 혹은 jpaRepository. CRUD가 기본적으로 구현된 class

	EmailVO findByEmsSeq(long ems_seq); // hibernate에서 메서드 이름으로 Snake Case를 인식 못하기 때문에 Camel Case로 변경

	// 매개변수는 VO에 있는 필드변수 순서로 들어간다
//	List<EmailVO> findAllByFromEmail(String from_email); // findAllBy 리스트, findBy 단일
//	List<EmailVO> findAllByFromEmailOrderByEmsSeqAsc(String from_email); // findAllBy 리스트, findBy 단일
//	List<EmailVO> findAllBySendDateGreaterThan(String send_date); // GreaterThan >, LessThan <
//	List<EmailVO> findAllBySendDateLessThan(String send_date); // GreaterThan >, LessThan <
//	
//	List<EmailVO> findAllByFromEmailAndFromName(@Param("from_email") String email, @Param("from_name") String name);
//	
//	List<EmailVO> findAllByFromEmailOrFromName(@Param("from_email") String email, @Param("from_name") String name);
}
