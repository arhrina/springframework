<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<style>
.in-errors {
	color:red;
	font-size:8px;
}
/*
	col-md-7 col-12
	해상도가 768보다 크면 7칸만 차지하고
	그 이하이면 12칸을 차지하여 풀 width 로 보여라
*/

tr,td,th {
	white-space: nowrap;

}
.pro-list {
	overflow: auto;
}

.p_name {
 	display:inline-block;
 	width:150px;
 	padding: 0 5px;
 	overflow: hidden;
 	text-overflow: ellipsis;
 	white-space: nowrap;
}

</style>
<article class="mt-5 row">
	<article class="col-md-7 col-12 bg-light pro-input">
	
		<form:form 
			action="${rootPath}/admin/product/input" 
				modelAttribute="productVO">

			<div class="form-group">
				<div class="container-fluid row">
					<form:select path="p_bcode" class="custom-select-sm col-6">
						<form:option value="0">품목을 선택하세요</form:option>
						<form:option value="B0001">공산품</form:option>
						<form:option value="B0002">농산물</form:option>
						<form:option value="B0003">수산물</form:option>
					</form:select>
					
					<form:select path="p_dcode" class="custom-select-sm col-6">
						<form:option value="0">거래처를 선택하세요</form:option>
						<form:option value="D0001">대덕물산</form:option>
						<form:option value="D0002">삼성농산</form:option>
						<form:option value="D0003">목포수산</form:option>
					</form:select>
				</div>
				
				<div class="container-fluid row">
					<form:errors path="p_bcode" class="in-errors col-6"/>
					<form:errors path="p_dcode" class="in-errors col-6"/>
				</div>		
			</div>
			<div class="form-group">
				<form:input path="p_code" 
					class="form-control" placeholder="상품코드" />
				<form:errors path="p_code" class="in-errors" />
			</div>
			<div class="form-group">
				<form:input path="p_name" 
					class="form-control" placeholder="상품이름" />
				<form:errors path="p_name"  class="in-errors" />
			</div>
			<div class="container-fluid form-group row">
				<form:input path="p_iprice" 
						class="form-control col-6" placeholder="매입단가" />
				<form:errors path="p_iprice"  class="in-errors" />
				
				<form:input path="p_oprice" 
						class="form-control col-6" placeholder="판매단가" />
				<form:errors path="p_oprice"  class="in-errors" />
			</div>
			<div class="form-group">
				<form:textarea path="p_detail" 
					class="form-control" rows="" cols="" placeholder="상세정보"/>
			</div>
			<div class="form-group">
				<button>저장</button>
			</div>
		</form:form>
	</article>
	
	<article class="col-md-4 col-12 bg-light pro-list">
		<%@ include file="/WEB-INF/views/admin/product_list.jsp" %>
	</article>

</article>


