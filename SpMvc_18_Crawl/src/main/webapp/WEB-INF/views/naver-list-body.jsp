<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--

https://www.w3schools.com/bootstrap4/bootstrap_grid_system.asp
아래쪽에 grid option

col-lg : >= 992px
	col-lg-2는 만약 width가 992px보다 큰 상태면 2/12만큼의 크기를 1개의 box로 설정
col-md : >= 768px
	col-md-4는 width가 992px보다 작고 768px보다 큰 상태면 4/12만큼의 크기를 1개의 box로 설정
col-sm : >= 576px
	col-sm-12는 width가 768px보다 작고 576px보다 큰 상태면 12/12 가로방향 1칸짜리 box를 설정
	
	sm보다 작은 width일 경우에는 sm 형식의 설정값이 유지된다
	
해상도에 따라 설정해주면 해상도가 바뀌었을 때 알아서 조절된다
 -->
<div class="col-lg-2 col-md-4 col-sm-12">
	<div class="card">
		<div class="card-header">${NAVER.m_title}</div>
		<div class="card-body">
			<img src="${NAVER.m_img_url}" width="100%">
		</div>
		<div class="card-footer">
			<a href="${NAVER.m_detail_url}">자세히 보기</a>
		</div>
	</div>
</div>