<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
</head>
<body>
<p><a href="${rootPath}/parameter/update?id=10">나는 id가 10번</a></p>
<!-- update나 delete를 호출할 때 query 변수명은 dto에서 사용하지 않는 변수명으로 쓸 것
이름이 같으면 String이 get할때 이름이 같은 것들을 모두 같이 가져간다 -->
</body>
</html>