<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="POST" action="${rootPath}/rbooks/insert">
		<input type="text" name="rb_date" placeholder="독서일자">
		<input type="text" name="rb_stime" placeholder="독서 시작시간"> <input
			type="number" name="rb_rtime" placeholder="독서시간"> <input
			type="text" name="rb_subject" placeholder="한줄소감"> <input
			type="text" name="rb_text" placeholder="긴줄소감"> <input
			type="number" name="rb_star" placeholder="별점">
		<button>추가</button>
	</form>
</body>
</html>