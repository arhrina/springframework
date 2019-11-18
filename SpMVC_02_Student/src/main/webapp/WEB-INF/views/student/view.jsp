<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>학생 정보 보기</h2>
<p>학번 : ${stDTO.st_num}
<!-- stDTO는 컨트롤러에 model에 addAttribute한 이름과 변수형을 받은 것. 랜더링이라고 한다 -->
<p>이름 : ${stDTO.st_name}
<p>학과 : ${stDTO.st_dept}
<p>학년 : ${stDTO.st_grade}
<p>전화번호 : ${stDTO.st_tel}
</body>
</html>