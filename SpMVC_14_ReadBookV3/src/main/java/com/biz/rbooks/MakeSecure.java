package com.biz.rbooks;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class MakeSecure {
	private final static String saltPass = "YOUR SALT"; // salt 문자열은 암호화키이므로 잘 숨길 것
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		StandardPBEStringEncryptor pbEnc = new StandardPBEStringEncryptor();
		
		Map<String, String> envList = System.getenv(); // 운영체제의 환경변수 가져오기
		
		System.out.print("UserID >> ");
		String pUserId = s.nextLine();
		System.out.print("Password >> ");
		String pPassword = s.nextLine();
		
		String envString = envList.get("ORACLEPATH"); // 운영체제 환경변수 중 ORACLEPATH로 등록된 값 가져오기
		
		if(envString == null) { // ORACLEPATH가 없으면 saltPass 문자열 가져오기
			envString = saltPass;
		}
		
		pbEnc.setAlgorithm("PBEWithMD5AndDES");
		pbEnc.setPassword(envString);
		String encUserId = pbEnc.encrypt(pUserId);
		String encPassword = pbEnc.encrypt(pPassword);
		
		System.out.printf("UserID : %s, %s\n", pUserId, encUserId);
		System.out.printf("Password : %s, %s", pPassword, encPassword);
		
		String strUserId = String.format("oracle.username=ENC(%s)", encUserId);
		String strPassword = String.format("oracle.password=ENC(%s)", encPassword);
		
		String proFileName = "./src/main/webapp/WEB-INF/spring/oracle.sec.properties"; 
		try {
			PrintWriter pw = new PrintWriter(proFileName);
			pw.println(strUserId);
			pw.println(strPassword);
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.close();
	}
}
