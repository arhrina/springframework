<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
// 여기는 JSP 내에 자바를 작성하는 영역
int num1 = 30;
int num2 = 40;
int sum = num1 + num2;
System.out.println(sum);
%>
<!DOCTYPE html>
<html>
<!-- head는 페이지에 대한 정보들을 기록한다
웹페이지에 표시되지는 않지만 표시하기 위한 다양한 설정 등을 기록해둔다 -->
<head>
<meta charset="UTF-8">
<title>FIRST HOMEPAGE</title>
<!-- 웹페이지를 표시할 때 제목으로 사용되는 문자열 -->

</head>

<!-- 웹페이지에 나타날 것들을 입력. 디자인과 관련된 문서표기 -->
<body>

<!-- h는 h1~h6까지 사용. 본문보다 큰 글자 표기 -->
<h2>나는 home.jsp</h2>
<h3>반갑습니다</h3>
<h4>우리나라 만세</h4>
<h4><%= sum %></h4>
<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nisl tincidunt eget nullam non. Quis hendrerit dolor magna eget est lorem ipsum dolor sit. Volutpat odio facilisis mauris sit amet massa. Commodo odio aenean sed adipiscing diam donec adipiscing tristique. Mi eget mauris pharetra et. Non tellus orci ac auctor augue. Elit at imperdiet dui accumsan sit. Ornare arcu dui vivamus arcu felis. Egestas integer eget aliquet nibh praesent. In hac habitasse platea dictumst quisque sagittis purus. Pulvinar elementum integer enim neque volutpat ac.
Senectus et netus et malesuada. Nunc pulvinar sapien et ligula ullamcorper malesuada proin. Neque convallis a cras semper auctor. Libero id faucibus nisl tincidunt eget. Leo a diam sollicitudin tempor id. A lacus vestibulum sed arcu non odio euismod lacinia. In tellus integer feugiat scelerisque. Feugiat in fermentum posuere urna nec tincidunt praesent. Porttitor rhoncus dolor purus non enim praesent elementum facilisis. Nisi scelerisque eu ultrices vitae auctor eu augue ut lectus. Ipsum faucibus vitae aliquet nec ullamcorper sit amet risus. Et malesuada fames ac turpis egestas sed. Sit amet nisl suscipit adipiscing bibendum est ultricies. Arcu ac tortor dignissim convallis aenean et tortor at. Pretium viverra suspendisse potenti nullam ac tortor vitae purus. Eros donec ac odio tempor orci dapibus ultrices. Elementum nibh tellus molestie nunc. Et magnis dis parturient montes nascetur. Est placerat in egestas erat imperdiet. Consequat interdum varius sit amet mattis vulputate enim.
Sit amet nulla facilisi morbi tempus. Nulla facilisi cras fermentum odio eu. Etiam erat velit scelerisque in dictum non consectetur a erat. Enim nulla aliquet porttitor lacus luctus accumsan tortor posuere. Ut sem nulla pharetra diam. Fames ac turpis egestas maecenas. Bibendum neque egestas congue quisque egestas diam. Laoreet id donec ultrices tincidunt arcu non sodales neque. Eget felis eget nunc lobortis mattis aliquam faucibus purus. Faucibus interdum posuere lorem ipsum dolor sit.
<p>군인은 현역을 면한 후가 아니면 국무위원으로 임명될 수 없다. 대통령은 국민의 보통·평등·직접·비밀선거에 의하여 선출한다. 국회는 의장 1인과 부의장 2인을 선출한다. 모든 국민은 법률이 정하는 바에 의하여 국가기관에 문서로 청원할 권리를 가진다. 대한민국의 주권은 국민에게 있고, 모든 권력은 국민으로부터 나온다.

국가는 모성의 보호를 위하여 노력하여야 한다. 대한민국은 민주공화국이다. 국가는 국민 모두의 생산 및 생활의 기반이 되는 국토의 효율적이고 균형있는 이용·개발과 보전을 위하여 법률이 정하는 바에 의하여 그에 관한 필요한 제한과 의무를 과할 수 있다. 모든 국민은 그 보호하는 자녀에게 적어도 초등교육과 법률이 정하는 교육을 받게 할 의무를 진다.

감사위원은 원장의 제청으로 대통령이 임명하고, 그 임기는 4년으로 하며, 1차에 한하여 중임할 수 있다. 형사피고인은 유죄의 판결이 확정될 때까지는 무죄로 추정된다. 정당은 그 목적·조직과 활동이 민주적이어야 하며, 국민의 정치적 의사형성에 참여하는데 필요한 조직을 가져야 한다. 위원은 정당에 가입하거나 정치에 관여할 수 없다.
<p>paragraph
<p>문단tag</p>
<p>본문tag</p>
<p>우리나라<br/>
대한민국<br/>
Republic of Korea</p>
<p>웹페이지에서는 Enter의 의미가 없다
<p>문서를 작성하면서 엔터를 입력해도 웹페이지에서는 한개의 빈칸으로만 인식
<p> 한개의 문단내의 문자열이 매우 커서 한 줄에 표시가 되지 않을 때는 자동으로 다음줄로 넘겨져 표시된다
이러한 현상을 Auto Word Wrap이라고 한다
<p>문자열을 표시하면서 br Tag를 사용하면 해당 위치에서 강제줄바꿈. br은 임의로 문단 내에서 문자열을 여러줄에 표시하고자 할 때
<p>br로 문자열의 줄바꿈을 하면<br/>문단 내에서는 위아래 여백이 없는 상태로 표시된다

</body>
</html>