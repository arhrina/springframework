package com.biz.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.app.ScoreVO;
import com.biz.app.service.NumService;

@RequestMapping(value="/number")
@Controller
public class NumController {

	/*
	 * autowired가 붙은 인스턴스는 스프링 프레임워크가 알아서 초기화를 해준다
	 * NumService nService = new NumService();를 생성자 등에서 해줄 필요가 없어진다
	 */
	@Autowired
	NumService nService;
	
	@ResponseBody
	@RequestMapping(value="/add",produces = "text/html;charset=UTF-8")
	public String add() {
		
		// NumService num = new NumService();
		// int ret = num.add(30, 40);
		
		int ret = nService.add(30, 40);
		return "두수의 덧셈: " + ret;
	}
	
	/*
	 * 사용자가 /number/even 이라고 요청을 하면
	 * 1~100까지 숫자 중에서 짝수의 덧셈만 수행하여 결과를 알려주겠다
	 */
	@ResponseBody
	@RequestMapping(value="/even",produces = "text/html;charset=UTF-8")
	public String even() {
		
		int start  = 1;
		int end = 100;
		
		// service에게 요청을 해서 짝수 덧셈을 수행해달라라고 할것
		int even = nService.even(start,end);
		
		String res = 
				String.format("%d 부터 %d까지의 "
						+ "숫자중 짝수의 합 : %d",start,end,even );
		return res;
		
	}

	/*
	 * 사용자가 요청한 변수=값의 형태는 무조건 값이 문자열이다
	 * 만약 매개변수 type int 형으로 선언을 하면
	 * spring은 사용자의 변수를 수신한 후 
	 * Integer.valueOf(변수) 코드를 실행하여
	 * 문자열을 숫자로 변환 시키려 시도를 한다.
	 * 
	 *  그런데 수신한 값이 숫자로 변환이 불가능하면
	 *  사용자에게 400 오류를 전송
	 */
	@ResponseBody
	@RequestMapping(value="/num2even",produces = "text/html;charset=UTF-8")
	public String even(String start, String end) {
		
		int intStart = 0;
		int intEnd = 0;
		try {
			 intStart = Integer.valueOf(start);
			 intEnd = Integer.valueOf(end);
		} catch (Exception e) {
			return "전송된 값을 숫자로 변환할 수 없습니다" ;
		}

		int even = nService.even(intStart,intEnd);
		String res = 
				String.format("%d 부터 %d까지의 "
						+ "숫자중 짝수의 합 : %d",intStart,intEnd,even );
		return res;
		
	}
	
	/*
	 * 국어, 영어, 수학, 과학, 음악 점수를 
	 * 		request로 받아서
	 * 총점과 평균을 계산한 후
	 * 		response 하고자 한다.
	 */

	// 메서드의 리턴은 하나밖에 올 수 없다
	@ResponseBody
	@RequestMapping(value="/score", produces = "text/html;charset=UTF-8")
	public String score(String kor, String eng, String mat, String sci, String mus) {
		int intKor, intEng, intMat, intSci, intMus;
		intKor = 0;
		intEng = 0;
		intMat = 0;
		intSci = 0;
		intMus = 0;
		try {
			intKor = Integer.valueOf(kor);
			intEng = Integer.valueOf(eng);
			intMat = Integer.valueOf(mat);
			intSci = Integer.valueOf(sci);
			intMus = Integer.valueOf(mus);
		} catch (Exception e) {
			return "전송된 값 중 숫자로 변환할 수 없는 값이 있습니다";
		}
		int sum = nService.sumSub(intKor, intEng, intMat, intSci, intMus);
		int avg = nService.avgSub(intKor, intEng, intMat, intSci, intMus);
		
		return "국어, 영어, 수학, 과학, 음악 다섯과목 점수의 합은 " + sum + " 평균은 " + avg + " 입니다";
	}
	
	
	/*
	 * 렌더링(Rendering)
	 * 
	 * 매개변수로 Model 클래스를 설정하고
	 * model 객체에 addAttribute("변수명", 값) 형식으로 내용을 추가하고
	 * jsp 파일을 return하면(responseBody가 없이 리턴하면) spring, tomcat은 model에 담겨있는 값과
	 * jsp 파일을 비교하여 연관된 변수들을 변환해서 HTML 코드로 생성한다
	 */
	
	
	// @ResponseBody
	@RequestMapping(value="/score_jsp", produces = "text/html;charset=UTF-8")
	public String score_jsp(String kor, String eng, String mat, String sci, String mus, Model model) {
		int intKor, intEng, intMat, intSci, intMus;
		intKor = 0;
		intEng = 0;
		intMat = 0;
		intSci = 0;
		intMus = 0;
		try {
			intKor = Integer.valueOf(kor);
			intEng = Integer.valueOf(eng);
			intMat = Integer.valueOf(mat);
			intSci = Integer.valueOf(sci);
			intMus = Integer.valueOf(mus);
		} catch (Exception e) {
			return "전송된 값 중 숫자로 변환할 수 없는 값이 있습니다";
		}
		int sum = nService.sumSub(intKor, intEng, intMat, intSci, intMus);
		int avg = nService.avgSub(intKor, intEng, intMat, intSci, intMus);
		
		model.addAttribute("SUM", sum); // SUM이라는 변수에 sum에 담긴 값을 전달
		model.addAttribute("AVG", avg); // AVG라는 변수에 avg에 담긴 값을 전달
		
		
		// return "국어, 영어, 수학, 과학, 음악 다섯과목 점수의 합은 " + sum + " 평균은 " + avg + " 입니다";
		
		
		
		
		// responsebody가 없으므로 return score는 views폴더에 score.jsp를 찾아라
		// jsp가 없으면 404 에러가 뜬다. views 폴더에 jsp 파일을 생성
		
		// score.jsp 파일을 읽어서 모델에 담아 전달된 변수들과 렌더링을 수행하라
		return "score";
		// score.jsp에서 ${SUM} 등의 EL tag로 만들어진 변수에 값을 담아 표시해준다
		
		// URI 뒤에 kor=80&eng=60&mat=70&sci=75&mus=90 등의 값을 일일이 날려주는 것도 번거로운 일이므로
		// score_input.jsp 파일을 생성
	}
	
	/*
	 * 학생의 점수를 입력하여 총점과 평균을 계산하고 싶다고 요청(localhost:8080/app/number/score_input)
	 * 그러면 학생의 과목 점수를 입력할 수 있는 화면을 보내는 역할을 수행
	 */
	@RequestMapping(value="/score_input", method=RequestMethod.GET) // GET방식으로 요청할 때만 응답하라
	public String scoreInput() { // score_input란 URL이 입력되면 그냥 score_input.jsp를 띄워주는 역할만을 하는 메서드
		return "score_input";
	}
	
	
	@RequestMapping(value="/score_input", method=RequestMethod.POST) // POST 방식으로 요청할 때만 응답하라
	public String scoreInput(ScoreVO scoreVO, Model model) { // scoreVO가 데이터를 한꺼번에 수신한다
		nService.score(scoreVO); // 서비스에 vo를 전달
		
		model.addAttribute("scoreVO", scoreVO); // scoreVO라는 변수명에 scoreVO를 담아서 score_input에 전달
		
		return "score_input";
	}
	
	
	/*
	 * 스프링에서 컨트롤러에 있는 메서드 이름은 중요하지 않다. mapping의 value가 중요하다
	 * 같은 이름의 value가 있으면 spring은 에러가 나타난다. method로 구분한다(HTTP 상의 메서드. 본 컨트롤러상의 메서드가 아님)
	 * 
	 * GET, POST가 있다
	 * 
	 * GET 방식 메서드는 주소창에 입력했을 때 실행
	 * HTML의 form에서는 GET이나 POST를 선택해서 보낼 수 있다
	 */
	
	
}



