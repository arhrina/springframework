<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css"
	href="${rootPath}/css/list-table.css?ver=2019-11-31-001" >
	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	$(function(){
		$(".content-body").click(function(){
			// td들의 목록을 추출
			let td = $(this).children()
			
			let strDCode = td.eq(1).text()
			let strDName = td.eq(2).text()
			let strDCeo = td.eq(3).text()
			
			// opener.document : 검색창을 열어준 view의 요소에 값을 write
			$(opener.document).find("#io_dcode").val(strDCode)
			$(opener.document).find("#io_dname").text(strDName + "(" + strDCeo + ")") // 거래처(대표이름)
			// 클릭후에 현재 열려있는 검색창을 닫기
			window.close()
			// IE 호환성을 위해 사용하는 코드
			window.open("about:blank", "_self").self.close()
		})
	})
</script>
<style>
	div.s-box{
		width: 95%;
		margin: 0 auto;
	}
	div.s-box input{
		padding: 8px;
		width: 100%;
	}
</style>
<article>
	<div class="s-box">
		<form>
			<input name="strText">
			<!-- 변수명을 동일하게 해서 action은 이 창의 query가 그대로 들어간다
			2개가 넘어갈 경우엔 버튼이 필요 -->
		</form>
	</div>
	<table>
		<tr>
			<th>NO</th>
			<th>거래처코드</th>
			<th>거래처명</th>
			<th>대표</th>
			<th>전화번호</th>
			<th>주소</th>
		</tr>
		<c:choose>
			<c:when test="${empty DEPTLIST}">
				<tr>
					<td colspan="5">데이터가 없음</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${DEPTLIST}" var="dto" varStatus="info">
					<tr class="content-body" id="${dto.d_code}">
						<td>${info.count}</td>
						<td>${dto.d_code}</td>
						<td>${dto.d_name}</td>
						<td>${dto.d_ceo}</td>
						<td>${dto.d_tel}</td>
						<td>${dto.d_addr}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
</article>