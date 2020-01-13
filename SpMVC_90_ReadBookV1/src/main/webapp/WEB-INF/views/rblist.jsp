<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<script>
$(function(){
	$(".btn-insert").click(function(){
		let id = $(this).attr(data-bcode)
		document.location.href = "${rootPath}/rbooks/insert?seq" + id
	})
	
	$(".btn-delete").click(function(){
		let id = $(this).attr(data-id)
		if(confirm("삭제?"))
		document.location.href = "${rootPath}/rbooks/delete?seq" + id
	})
})
</script>
<body>
	<c:choose>
		<c:when test="${empty rblist}">
			<tr>
				<td colspan="7">독서록 자료가 없음</td>
		</c:when>
		<c:otherwise>
			<c:forEach items="${rblist}" var="rb" varStatus="index">
				<tr class="content-body" data-id="${rb.rb_seq}" data-bcode="${rb.rb_bcode}">
					<td>${rb.rb_bcode}</td>
					<td>${rb.rb_date}</td>
					<td>${rb.rb_stime}</td>
					<td>${rb.rb_rtime}</td>
					<td>${rb.rb_subject}</td>
					<td>${rb.rb_text}</td>
					<td>${rb.rb_star}</td>
					<td><button type="button" class="btn-delete">삭제</button></td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	<button class="btn-insert">독서록 추가</button>
</body>
</html>