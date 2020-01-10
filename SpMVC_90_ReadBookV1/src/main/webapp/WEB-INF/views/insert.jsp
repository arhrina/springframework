<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 추가 페이지</title>
</head>
<body>

	<c:choose>
		<c:when test="${empty bookVO}">
			<form method="POST" action="${rootPath}/books/insert">
				<input type="text" name="b_code" placeholder="도서코드 - 입력테스트용">
				<input type="text" name="b_name" placeholder="책 제목"> <input
					type="text" name="b_author" placeholder="저자"> <input
					type="text" name="b_comp" placeholder="출판사"> <input
					type="text" name="b_year" placeholder="구입일자"> <input
					type="number" name="b_page" placeholder="페이지수"> <input
					type="number" name="b_iprice" placeholder="구입가격">
				<button>추가</button>
			</form>
		</c:when>
		<c:otherwise>
			<form method="POST" action="${rootPath}/books/update">
				<input type="text" name="b_code" value="${bookVO.b_code}"> <input
					type="text" name="b_name" value="${bookVO.b_name}"> <input
					type="text" name="b_author" value="${bookVO.b_author}"> <input
					type="text" name="b_comp" value="${bookVO.b_comp}"> <input
					type="text" name="b_year" value="${bookVO.b_year}"> <input
					type="number" name="b_page" value="${bookVO.b_page}"> <input
					type="number" name="b_iprice" value="${bookVO.b_iprice}">
				<button>수정</button>
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>