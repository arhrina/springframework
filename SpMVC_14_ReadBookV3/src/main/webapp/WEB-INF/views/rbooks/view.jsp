<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<%@ include file="/WEB-INF/views/include/include-head.jspf" %> <!-- include하는 파일들이 많아졌으므로 파일로 따로 분리해서 로드 -->
<script>
$(function() { // 파일 width 크기 조절해가면서 별 표시
	let a = "${r_book.rb_star}"
	
	if(a == "") a = 1
	else parseInt(a)
	a = a * 10
	
	$("span.star-red").css("width", a + "%")
	
	$("button").click(function(){ // 클릭 이벤트를 버튼 전체에 주고 버튼 id로 구분해서 url을 만들고 실행 
		let btn_id = $(this).attr("id")
		let url = $"rootPath/rbook/"
		if(btn_id == "btn-update") {
			url += "update/${RBOOK.rb_seq}"
		}
		else if(btn_id == "btn-delete") {
			url += "delete/${RBOOK.rb_seq}"
		}
		else if(btn_id == "btn-list") { // url 문자열을 쓰지 않고 바로 url로 보내고 else if문 끝내기 위한 return false
			document.location.href = "${rootPath}/rbook/list"
			return false
		}
		document.location.href = url
	})
	
})
</script>
<style>
	.input-box {
		display: flex;
		width: 80%;
		margin: 5px auto;
	}
	
	.input-box input {
		color: black;
		border: none;
		border-bottom: 1px solid rgb(52, 152, 219);
		display: block;
		font-size: 0.9rem;
		width: 100%;
		padding: 8px;
	}
	
	#rb_bcode{
		width: 50%;
		margin-right: 5px;
	}
	
	article {
		display: flex;
		flex-flow: wrap;
	}
	
	div {
		display: inline-block;
		width: 70%;
		padding: 5px 16px;
		margin: 5px;
		background-color: #eee;
	}
	
	div.title-box {
		width: 25%;
		background-color: #ccc;
		text-align: right;
	}
	
	span.star-box {
		width: 100px;
	}
	
	span.star-box, span .star-red { /* 이미지로 별을 가져와서 별을 표시 */
		display: inline-block;
		height: 20px;
		overflow: hidden;
		background: url("${rootPath}/image/star.png") no-repeat;
		background-size: 100px 40px; /* 배경이미지의 width는 100px, heigh를 40px로 */
	}
	
	span.star-red {
		background-position: left bottom; /* 배경이미지를 채울 때, 왼쪽 아래 꼭지점을 기준으로 배치 */
		line-height: 0;
		vertical-align: top;
		width: 50%;
	}
	
	div.text-box {
		width: 100%;
	}
	
</style>
</head>
<body>
	<header>
		<h2>MY READ BOOK</h2>
	</header>
	<%@ include file="/WEB-INF/views/include/include-nav.jspf"%>
	
	<section>
		<%@ include file="/WEB-INF/views/books/book-view.jsp" %>
		<article>
			<!--  <div class="title-box">도서정보</div>
			<div>${r_book.rb_bcode}, ${r_book.rb_bname}</div>
			-->
			
			<div class="title-box">읽은 때</div>
			<div>${r_book.rb_date}, ${r_book.rb_stime}부터 ${r_book.rb_rtime}시간동안</div>
			
			<div class="title-box">한줄평</div>
			<div>${r_book.rb_subject},
				<span class="star-box">
					<span class="star-red"></span>
				</span>
			</div>
			<div class="text-box">
				${r_book.rb_text}
			</div>
		</article>
		<article>
			<button id="btn-update" type="button" class="flex-right biz-blue">수정</button>
			<button id="btn-delete" type="button" class="biz-red">삭제</button>
			<button id="btn-list" type="button" class="biz-orange">리스트보기</button>
		</article>
	</section>
</body>
</html>