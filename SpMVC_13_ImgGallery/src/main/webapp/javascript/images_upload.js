/*function funcA(text) { // 함수에 함수이름(매개변수)
	alert("나는 function A" + text)
}

// const
var funcB = function(test) { // 변수선언처럼 함수를 선언 = 객체를 변수로 선언하는 것과 같은 원리
	// 함수를 변수로 선언할 수 있는 몇 가지 언어 중 하나. js함수를 1급함수라고 한다
	// 매개변수로 함수를 사용할 수도 있다
	alert("나는 function B")
}

var funcC = ()-> { // 화살표함수. JS 2015 ver부터 사용가능. 브라우저별 호환성 이슈 존재
	alert("나는 function C")
}

var funcD = (text)-> {
	alert("나는 function D")
}

function funcTest() {
	funcA("우리나라")
	funcB("KOREA")
	
	funcA()
	funcB()
}
*/
var files_up = function(formData) {

	$.ajax({
		url : rootPath + '/image/files_up',
		method : 'POST',
		data : formData,

		processData : false, /* 파일업로드 필수 옵션 */
		contentType : false, /* 파일업로드 필수 옵션 */

		success : function(result) {
			if (result == 'FILE UPLOAD FAIL') {
				alert("파일 업로드 오류")
			} else {
				$("#d_d_box").html(result)
			}

		},
		error : function() {
			alert("서버 통신 오류")
		}
	})
}