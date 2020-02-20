package com.biz.app.service;

import org.springframework.stereotype.Service;

import com.biz.app.ScoreVO;

/*
 * Service 클래스
 * @Service Annotation을 설정한 클래스
 * 
 * Controller가 사용자의 요청을 받았는데
 * 단순한 연산을 수행해서 결과를 보낼 사안이 아닐때
 * 1. 조금 복잡한 무언가를 수행해야할때
 * 		Controller의 기능을 보조하는 역할을 수행한다. 
 * 
 * 
 */
@Service
public class NumService {
	
	public int add(int num1, int num2) {
		int sum = 0 ;
		sum = num1 + num2;
		return sum;
	}

	public int even(int start, int end) {

		int sum = 0;
		for(int i = start ; i <= end ; i++) {
			if(i % 2 == 0) {
				sum += i;
			}
		}
		return sum;
	}
	
	public int sumSub(int a, int b, int c, int d, int e) {
		return a + b + c + d + e;
	}

	public int avgSub(int intKor, int intEng, int intMat, int intSci, int intMus) {
		// TODO Auto-generated method stub
		return (sumSub(intKor, intEng, intMat, intSci, intMus)) / 5;
	}

	public void score(ScoreVO scoreVO) {
		// TODO Auto-generated method stub
		int sum = scoreVO.getKor();
		sum += scoreVO.getEng();
		sum += scoreVO.getMat();
		sum += scoreVO.getSci();
		sum += scoreVO.getMus();
		
		scoreVO.setSum(sum);
		scoreVO.setAvg(sum / 5);
		}
	
}








