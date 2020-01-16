<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">

<!--include file="/WEB-INF/views/include/include-head.jspf"가 두개 이상 include 되므로 삭제 -->

<script>
$(function(){
	$("#search-table tr").click(function() { // search-table에서 tr 아무거나 누르면 동작. 새창을 띄우지 않고 modal에서 처리
		
		let trs = $(this).children() // 클릭된 VO의 내용 가져오기
		
		let bCode = trs.eq(0).text() // 전체리스트 중에 0번째
		let bName = trs.eq(1).text() // 1번째만 가져오기
		
		$("#rb_bcode").val(bCode)
		$("#rb_bname").val(bName)
		
		$("#modal-box").css("display", "none") // 누르고나서 자료를 넘겨주면 modal-box는 없어지도록
	})
})
</script>
</head>
<body>
	<section id="search-list">
		<table id="search-table">
			<thead>
				<tr>
					<th>도서코드</th>
					<th>도서이름</th>
					<th>출판사</th>
					<th>저자</th>
					<th>구입일자</th>
					<th>구입가격</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${b_List}" var="book">
					<tr>
						<td>${book.b_code}</td>
						<td>${book.b_name}</td>
						<td>${book.b_comp}</td>
						<td>${book.b_auther}</td>
						<td>${book.b_year}</td>
						<td>${book.b_iprice}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
</body>
</html>