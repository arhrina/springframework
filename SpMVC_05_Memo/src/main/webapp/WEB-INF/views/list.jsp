<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!-- link rel에서 ${rootPath}를 쓰기 위한 정의코드 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="${rootPath}/css/list-table.css">
	<table>
		<tr>
			<th>SEQ</th>
			<th>작성일</th>
			<th>작성시간</th>
			<th>작성자</th>
			<th>카테고리</th>
			<th>제목</th>
		</tr>
		<c:choose>
			<c:when test="${MEMO_LIST eq NULL}"> <!-- EL tag에서 eq는 ==과 같다 -->
				<tr><td colspan="6">메모가 없음</td></tr>
			</c:when>
			<c:otherwise>
			<!-- 위에 있는 tr과 데이터를 맞춰주는 용도의 for문 -->
				<c:forEach items="${MEMO_LIST}" var="memo" varStatus="index">
					<tr class="content-body" data-id="${memo.m_seq}">
						<td>${index.count}</td>
						<td>${memo.m_date}</td>
						<td>${memo.m_time}</td>
						<td>${memo.m_auth}</td>
						<td>${memo.m_cat}</td>
						<td>${memo.m_subject}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
</body>
</html>