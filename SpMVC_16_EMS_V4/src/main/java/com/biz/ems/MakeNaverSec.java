package com.biz.ems;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class MakeNaverSec {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		StandardPBEStringEncryptor pbEnc = new StandardPBEStringEncryptor();
		
		// 환경변수 가져오기
		Map<String,String> envList = System.getenv();
		
		String saltPass = envList.get("NAVER");
		
		System.out.println("SaltPass : " + saltPass);
		
		System.out.print("Naver ID >> ");
		String naverId = scanner.nextLine();
		
		System.out.print("Naver Password >> " );
		String naverPass = scanner.nextLine();
		
		System.out.print("DB User >> " );
		String dbUserId = scanner.nextLine();
		
		System.out.print("DB Password >> " );
		String dbPassword = scanner.nextLine();
		
		// 암호화 설정
		pbEnc.setAlgorithm("PBEWithMD5AndDES");
		pbEnc.setPassword(saltPass);
		
		String encNaverId = pbEnc.encrypt(naverId);
		String encNaverPass = pbEnc.encrypt(naverPass);
		String encDBUserId = pbEnc.encrypt(dbUserId);
		String encDBPass = pbEnc.encrypt(dbPassword);
		
		System.out.printf("Naver, DB : %s, %s, %s, %s\n",naverId, naverPass, dbUserId, dbPassword);
		System.out.printf("EncNaver, EncDB : %s, %s, %s, %s\n",encNaverId, encNaverPass, encDBUserId, encDBPass);

		
		String saveNaverId 
			= String.format("naver.username=ENC(%s)",encNaverId);
		String saveNaverPass 
		= String.format("naver.password=ENC(%s)",encNaverPass);
		String saveDBUserId 
		= String.format("MySQL.username=ENC(%s)",encDBUserId);
		String saveDBPass 
		= String.format("MySQL.password=ENC(%s)",encDBPass);
		
		String profileName = "./src/main/webapp/"
							+ "WEB-INF/spring/"
							+ "naver.email.secret.properties";
		
		String WEB_INF = "./src/main/webapp/WEB-INF/spring";
		String naver_pro = "naver.email.secret.properties";
		File proFile = new File(WEB_INF,naver_pro);

		
		// PrintWriter out = new PrintWriter(profileName);
		try {
			PrintWriter out = new PrintWriter(proFile);
			out.println(saveNaverId);
			out.println(saveNaverPass);
			out.println(saveDBUserId);
			out.println(saveDBPass);
			
			out.flush();
			out.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanner.close();
		
	}
	
}
