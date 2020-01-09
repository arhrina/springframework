# 이미지 갤러리 프로젝트
* 2020-01-03

### WYSWYG(What you see What you Get) Editor
* summernote : https://summernote.org/

* textarea에 id 값을 설정하고 id에 jq를 이용해서 속성을 설정해주면 간편하게 WysWyg 방식으로 문서를 작성 할 수 있다.


### Drag and Drop으로 파일 올리기
1 Drag And Drop 을 수행할 box를 만들기
2 jq dragover, drop event를 설정
3 e.orrigalEvet.dataTrasnfer 로 부터 files 객체추출

4 files 객체의 0 번째 file 객체를 추출

5 ajax를 사용해서 서버로 파일을 업로드 수행하고
6 파일이름등을 다시 response로 되돌려 받아서
7 form img_file input box에 저장을 하고

8 내용을 form post로 upload하면 나머지 정보를 db에 저장

### Drag and Drop으로 여러개 파일 올리기
* 1 ~ 3까지는 single file upload와 동일
4.files 객체에 담긴 모든 file 객체를 formData에 append()

5.ajax를 사용해서 서버로 업로드를 수행하고
6.컨트롤러는 수신된 파일들을 서버에 저장하고
7.저장된 파일이름을 리스트로 생성하고
8.생성된 파일이름 리스트와 리스트를 표현할 jsp 를 renderging 하여 다시 return 하고
9.ajax success 코드에서는 return 받은 html 코드를
10.리스트를 표현할 box에 보인다.

#### 이때 리스트를 만들때
* hidden으로 파일리스트를 담을 input box를 만들고
* 각각의 파일이름을 input box에 value에 추가해 둔다

#### 저장을 하면
* 다시 컨트롤러에서는 본문(text)과 함께 hidden input box에 담긴 파일이름을 수신하는데
* String[] mFile : 문자열 배열로 수신하면 된다.

### form에서 같은 tag에 다중값을 담아서 controller 전달하기

#### 1. List<String>형식으로 전달하기

* form에서 같은 이름의 tag에 다중의 값을 담고
* <input name="title" value="1번">
* <input name="title" value="2번">
* <input name="title" value="3번">
	
* Controller에서
* String[] title 형식으로 매개변수 설정하여 받기

* VO내부에서 List<String> title 변수를 설정해 두고 VO와 같이 받기

### 2. List<SubVO>를 포함한 MainVO에 한꺼번에 받기

* form에서 같은 이름의 tag에 다중의 값을 담고
* <input name="main[0].title" value="1번">
* <input name="main[1].title" value="2번">
* <input name="main[2].title" value="3번">
 


### HttpSession
* Statusless : http(Hyper Text Transfer Protocol)는 특성상 request가 이루어지고 결과를 response하게되면 웹브라우저와 서버간에 어떠한 정보도 남지 않는다

* 통신에서는 같은 웹브라우저인 클라이언트에서 같은 주소로 서버에 자주 request를 수행하는 경우가 잦다
* 이때, 클라이언트가 요청한 request에 대한 정보를 서버가 참조하고 싶을 때가 있다(login 정보 등)
* 과거에는 cookie를 사용하여 사용자가 로그인을 수행하면 그 로그인정보를 cookie란 데이터 형태에 담아 클라이언트에 보내면, 클라이언트는 그 정보를 저장해두었다가 다시 request를 수행할 때 cookie를 request 정보에 담아 같이 전송했었다
* 서버에서는 req 정보에 cookie가 있다면 그 정보를 분석하여 행동을 결정했었다

* cookie 정보는 보통 암호화되지 않고 누구나 열어볼 수 있는 형태. 민감한 개인정보 등이 노출되기 쉬움. 중간에 가로채기를 통해 쿠키정보가 노출될 수도 있다. 이러한 단점을 보완하기 위해 Session이라는 개념이 도입되었다
* java 기반 서버(WAS)에선 HttpSession이라는 class를 만들어 쉽게 세션을 관리할 수 있도록 지원한다

* 서버는 필요시 HttpSession 객체에 Attribute를 추가하면 java에서 사용할 수 있는 어떠한 데이터라도 Session형 데이터로 변환해준다
* httpSession.setAttribute("MEMBER", memberVO) 형태를 사용하면 memberVO 객체에 담긴 모든 데이터가 서버 어딘가에 MEMBER라는 이름으로 Session ID를 생성해서 보관한다

* 이때 Session ID는 내부에서 특별한 방법으로 암호화 된 값으로 변환된다
* 서버에서 res를 수행하면 자동으로 ResponseBody에 Session ID값이 추가되어 client로 보내진다
* 클라이언트는 수신된 res 정보에 Session ID가 있으면 쿠키 영역에 임시보관
* 이후에 client에서 req를 보낼 때 Session ID를 추가하여 서버로 보낸다
* 서버(Spring)에서 req 정보에 Session ID 정보가 있으면 해당 Session 객체를 메모리에서 찾아보고 Session ID가 유효하면(HttpSession의 Session ID 유효기간은 명시하지 않으면 브라우저가 완전히 close 되거나 서버가 재시작 전까지 유효) 그 객체를 controller의 method에 사용한다

* Controller의 method에서는 HttpSession 클래스로 매개변수를 선언해두면 해당 객체의 Session 객체값이 담겨 있어 코드에서 사용할 수 있다

* SessionAttribute는 form과 controller 사이에서만 유효. 값을 만든 controller에서만 유효. HttpSession은 모든 컨트롤러에서 공유