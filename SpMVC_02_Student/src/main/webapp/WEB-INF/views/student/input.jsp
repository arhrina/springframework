<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>학생정보 입력</h2>
<!-- form에 action을 안넣으면 똑같은 주소로 보낸다 -->
<!-- form에 값을 입력하면서 변수명을 사용했고, 이 변수명은 DTO에 있으며 생성자, 겟,세터가 있으면
컨트롤러에 매개변수로 설정된 DTO가 request.getparameter를 쓸필요없이 자동으로 값을 수신한다 -->
<form method="POST">
	<p><label>학번 : </label><input name="st_num">
	<p><label>이름 : </label><input name="st_name">
	<p><label>학과 : </label><input name="st_dept">
	<p><label>학년 : </label><input name="st_grade">
	<p><label>전화번호 : </label><input name="st_tel">
	<p><button>저장</button>
</form>
</body>
</html>