package com.biz.ems;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class MakeNaverSec { // api 등 민감한 정보를 암호화한 파일을 만들기 위한 클래스
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		StandardPBEStringEncryptor pbEnc = new StandardPBEStringEncryptor();
		Map<String, String> envList = System.getenv(); // 운영체제에 있는 환경변수 불러오기

		// NAVER라는 환경변수명에 있는 값 불러오기. 보안을 위해 본인 컴퓨터에서만 동작하는 환경변수를 가져와서 활용한다
		String saltPass = envList.get("NAVER");
		
		System.out.printf("SaltPass : %s\n", saltPass); // 환경변수값 내용물 체크
		
		// id랑 비밀번호 입력받기
		System.out.print("NaverId >> ");
		String naverId = s.nextLine();
		
		System.out.print("NaverPassword >> ");
		String naverPass = s.nextLine();
		
		// 암호화 설정
		pbEnc.setAlgorithm("PBEWithMD5AndDES"); // 알고리즘
		pbEnc.setPassword(saltPass); // salt값
		
		// 암호화
		String encNaverId = pbEnc.encrypt(naverId);
		String encNaverPass = pbEnc.encrypt(naverPass);
		
		// 암호화가 잘 되는지 체크
		System.out.printf("Naver : %s %s\n", naverId, naverPass);
		System.out.printf("암호화 : %s %s\n", encNaverId, encNaverPass);
		
		// 암호화된 값으로 파일에 저장하기 위한 문자열
		String saveNaverId = String.format("naver.username = ENC(%s)", encNaverId);
		String saveNaverPass = String.format("naver.password = ENC(%s)", encNaverPass);
		
		// 파일로 저장하기 위한 경로설정
		// String profileName = "./src/main/webapp/WEB-INF/spring/naver.email.secret.properties";
		// profileName을 찢어서 문자열들로 잇기
		String WEB_INF = "./src/main/webapp/WEB-INF/spring";
		String naver_pro = "naver.email.secret.properties";
		
		File proFile = new File(WEB_INF, naver_pro);
		// path경로와 file이름. 실행될 운영체제에 맞게 구분separate문자열(윈도우는 \, linux는 /)을 변경시켜준다
		
		try {
			PrintWriter pw = new PrintWriter(proFile);
			pw.println(saveNaverId);
			pw.println(saveNaverPass);
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.close();
	}
}
