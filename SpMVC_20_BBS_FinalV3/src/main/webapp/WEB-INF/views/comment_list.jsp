<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<c:forEach var="c" items="${cList}">
	<div class="row p-2 bg-light cmt-item" data-id="${c.c_id}">
		<div class="col-2">
			<b>${c.c_writer}</b>
		</div>
		<div class="col-9">${c.c_subject}</div>
		<div class="col-1 cmt-item-del">&times;</div>
	</div>
</c:forEach>