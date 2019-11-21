<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>


<style type="text/css">
/* tag 이름을 적고 {}로 묶어서 모양에 대한 속성들을 입력. 속성은 대소문자구분. 각각에 세미콜론 필요 */

/* tag에 적용하는 방법, id에 적용하는 방법, class에 적용하는 방법으로 지정방식 3가지가 있다
tag에 적용 : tag 이름을 적는다. 본문의 모든 태그에 적용
id에 적용 : #id. tag#id나 #id나 id는 유일하므로 별다른 차이가 없다
class에 적용 : .class로 지정. 그룹, 묶음. 문서 전체의 클래스에 적용. tag.class는 해당 태그에 있는 클래스
 */
*, html, body { /* 기본적으로 설정되있는 margin, padding을 초기화 */
	margin: 0;
	padding: 0;
}

header {
	background-color: green; /* box 바탕색 지정 */
	color: white; /* box 안에 있는 글자색 지정 */
	margin: 0;
	padding: 1rem;
}

p {
	background-color: cyan;
	/* style sheet는 in-line style보다 우선순위가 낮다
p에 대한 스타일을 만들 경우, </p>를 꼭 만들어줘야 다른 곳에 영향을 적게 미친다 */
}
ul {
	display: flex;
	/* box model tag의 속성을 inline-box 속성으로 변경. 박스를 원하는 크기로 배치할 수 있다
	box model 페이지 왼쪽부터 오른쪽 끝까지 박스가 모두 채워진다
	inline box 옆으로 나열해서 해당하는 영역만큼을 채운다
	
	기존에는 박스를 원하는 곳에 배치하려면 float라는 기법을 사용했었고, 사용법이 까다로웠다
	 */
	list-style: none; /* list의 머릿글 제거 */
}
li {
	width: 100px;
	margin-right: 10px;
	background-color: green;
}

a {
	text-decoration: none; /* a tag에 적용되어 밑줄을 없애는 용도 */
	padding: 10px;
}

p#p1 { /* id에 대해 지정하여 style 적용. id가 중복되면 제대로 적용되지 않으므로 하나씩 id를 지정 */
	font-size: 50pt;
	background-color: blue;
	color: white;
}

#p2 { /* id에 대해 지정하여 style 적용. id가 중복되면 제대로 적용되지 않으므로 하나씩 id를 지정 */
	font-size: 20pt;
	background-color: green;
	color: red;
}

p.pc { /* p에 class가 pc로 지정된 tag에 스타일을 지정. 문서 전체 pc class에 지정하려면 .pc */
	background-color: red;
	color: white;
}

nav a:hover {
	/* nav 태그 안(특정 태그 안으로 범위를 좁힐 수 있다)의 a에만 마우스가 올라갔을 경우 스타일 */
	font-weight: bold; /* 글자 모양을 bold로 변경 */
	font-style: italic;
}
</style>
</head>
<body>
	<header>
		<h3>나의 홈페이지</h3>
	</header>
	<nav>
		<ul>
			<li><a href="#">학생리스트</a></li>
			<li><a href="#">학생검색</a></li>
			<li><a href="#">로그인</a></li>
			<li><a href="#">회원가입</a></li>
		</ul>
	</nav>
	<section>
		<article>
			<p>
				<font size=10pt color=blue face=궁서>여기는 본문</font>
			</p>
			<p>여기는 나의 이야기</p>
			<p id="p1">p1</p>
			<p id="p2">p2</p>
			<p id="p3" class="pc">p3</p>
			<p id="p4" class="pc">p4</p>
			<p id="p5" class="pc">p5</p>
			<p>
				<a href="https://naver.com/">네이버 바로가기</a>
				<a href="https://daum.net/">다음 바로가기</a>
			</p>
			<!-- 오래된 방식. in-line style. 문제가 있으므로 style로 작성한다 -->
			<p style="font-size: 50pt; color: red; background-color: yellow">또
				다른 본문</p>
		</article>
	</section>
	<footer>
		<address>CopyRight &copy; hyoukim@naver.com</address>
		<!-- 만든 사람 -->
	</footer>
</body>
</html>