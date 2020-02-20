<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Insert title here</title>
</head>
<body>
<h3>학생 점수 입력</h3>


<form action="/app/number/score_input" method=POST>
	<label>국어</label><input name="kor" placeholder="국어점수" value='<c:out value="${scoreVO.kor}" default="0"/>'><br>
	<label>영어</label><input name="eng" placeholder="영어점수" value='<c:out value="${scoreVO.eng}" default="0"/>'><br>
	<label>수학</label><input name="mat" placeholder="수학점수" value='<c:out value="${scoreVO.mat}" default="0"/>'><br>
	<label>과학</label><input name="sci" placeholder="과학점수" value='<c:out value="${scoreVO.sci}" default="0"/>'><br>
	<label>음악</label><input name="mus" placeholder="음악점수" value='<c:out value="${scoreVO.mus}" default="0"/>'><br>
	<button>계산</button>
</form>
<!-- 버튼을 누르면 알아서 score_input 페이지 뒤에 ?kor=입력된점수&eng=입력된점수 등등이 붙어서 보낸다
score_jsp 페이지에 보내고 싶으므로 form에 action을 붙여 score_jsp를 적는다 -->
<!-- 다수의 변수를 보내는 것이 불편하므로 VO를 만든다 -->


<!-- 값들을 담아 form에 묶고 배송받을 주소 action을 담았다. 안전하게 자료를 보내기 위해(외부에서 숨기기 위해) method=POST
를 사용한다. score_input의 POST에 보내고 싶게 하고 싶으므로 action을 score_input으로 지정해준다
 -->
 
<div><b>총점</b>:${scoreVO.sum}</div>
<!-- VO에 있는 값을 뽑아내므로 .을 사용 -->
<div><b>평균</b>:${scoreVO.avg}</div>

</body>
</html>




	<!-- value를 입력해두면 기존에 입력했던 값이 남는다. 대신 placeholder가 보이지 않는다
	이를 해결하기 위해 label을 붙인다 -->
	
	<!-- null값이 넘어가는 것을 방지 하기 위한 방법으로 default값을 설정해준다 -->