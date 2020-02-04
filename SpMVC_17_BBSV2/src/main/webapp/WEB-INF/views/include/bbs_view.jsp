<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<tr>
	<td>${bbsVO.bbs_id}</td>
	<td>${bbsVO.bbs_writer}</td>
</tr>
<tr>
	<td>${bbsVO.bbs_date}</td>
	<td>${bbsVO.bbs_time}</td>
</tr>
<tr>
	<td>${bbsVO.bbs_subject}</td>
	<td>${bbsVO.bbs_content}</td>
</tr>
<div class="btn-group">
	<button type="button" class="btn btn-primary">리스트보기</button>
	<button type="button" class="btn btn-success">수정</button>
	<button type="button" class="btn btn-warning">삭제</button>
	<button type="button" class="btn btn-dark">댓글</button>
</div>

<form:form action="${rootPath}/bbs/reply" modelAttribute="bbsVO">
	<form:textarea path="bbs_content" placeholder="댓글 입력" cols="" rows="10"/>
	<button class="btn btn-info">댓글 저장</button>
</form:form>