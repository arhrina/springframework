<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(function(){
		/*
		tr 태그 중 클래스 pro_tr를 클릭하면 id 값을 추출하고 update 메서드로 전달
		*/
		$(".pro_tr").click(function(){
			let id = $(this).data("id") // attr("data-id")
			// this는 .pro_tr을 지정했으므로 pro_tr 클래스만 이 이벤트와 관계가 있다.
			// pro_tr의 모든 것들이 this에 담긴다. $(this)는 (".pro_tr")의 모든 것들을 담는 내장 객체
			document.location.href="${rootPath}/admin/product/update/" + id // pathVariable로 보내기
//			document.location.href="${rootPath}/admin/product/update?id=" + id // requestParam으로 보내기
		// 따로 액션을 지정해주지 않으면 같은 이름으로 보내버리면 해당하는 값이 배열이 되어 action에 따라다니는 문제가 발생
		})
	})
</script>
<table class="col-md-4 col-12">
	<tr>
		<th>상품코드</th>
		<th>상품이름</th>
		<th>거래처</th>
		<th>품목</th>
		<th>매입가격</th>
		<th>판매가격</th>
	</tr>
	<c:choose>
		<c:when test="${empty PRO_LIST}">
			<tr>
				<td colspan="6">상품정보가 없습니다</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach var="PRO" items="${PRO_LIST}" varStatus="i">
			<tr class="pro_tr" data-id="${PRO.id}">
				<td>${PRO.p_code}</td>
				<td><span class="p_name">${PRO.p_name}</span></td>
				<td>${PRO.p_bcode}</td>
				<td>${PRO.p_dcode}</td>
				<td>${PRO.p_iprice}</td>
				<td>${PRO.p_oprice}</td>
			</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
		<div>
			<a href="/list?pageno=1&search=${search}&text=${text}">1</a>
			<a href="/list?pageno=2&search=${search}&text=${text}">2</a>
			<a href="/list?pageno=3&search=${search}&text=${text}">3</a>
			<a href="/list?pageno=4&search=${search}&text=${text}">4</a>
			<a href="/list?pageno=5&search=${search}&text=${text}">5</a>
		</div>
	
</table>


