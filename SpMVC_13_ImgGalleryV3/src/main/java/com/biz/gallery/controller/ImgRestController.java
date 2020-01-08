package com.biz.gallery.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.biz.gallery.domain.ImageFilesVO;
import com.biz.gallery.domain.ImageVO;
import com.biz.gallery.repository.ImageDao;
import com.biz.gallery.repository.ImageFileDao;
import com.biz.gallery.service.FileService;
import com.biz.gallery.service.ImageFileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value="/rest")
@RestController
public class ImgRestController {
	
	
	private final FileService fService;
	private final ImageFileService ifService;
	private final ImageDao imgDao;
	private final ImageFileDao ifDao;
	
	@Autowired
	public ImgRestController(FileService fService, ImageFileService ifService, ImageDao imgDao, ImageFileDao ifDao) {
		this.fService = fService;
		this.ifService = ifService;
		this.imgDao = imgDao;
		this.ifDao = ifDao;
	}

	@RequestMapping(value="/file_up",
			method=RequestMethod.POST,
			produces = "text/html;charset=UTF-8")
	public String file_up(
			@RequestParam("file") MultipartFile upfile) {
		
		String upLoadFileName = fService.file_up(upfile);
		
		if(upLoadFileName == null) return "FAIL";
		else return upLoadFileName;
		
	}
	
	// httpServletRequest는 클라이언트로부터 넘어오는 값을 받는다(Request), Response는 서버에서 클라이언트로 보내는 클래스
	/*
	 * fileDownload
	 * 1. 단순히 파일을 클릭했을 때 링크를 주고 다운로드(가장 기본. 오래된 방법). 우클릭-다른이름으로 저장
	 * 파일이름이 서버에 그대로 저장되며 그대로 다운로드. 서버에 대한 정보가 노출되기 쉬움
	 * 
	 * 2. response에 file을 실어서 보내고 web client 입장에서는 http protocol의 body에 실려오는 데이터를
	 * 수신하는 형태. 서버에 저장된 파일이 노출되지 않더라도 파일을 다운로드할 수 있다
	 * 이미지 이외의 파일일 경우 감춰진 폴더에 저장해뒀다가 별도의 다운로드 기능을 구현해 받게 한다 
	 * 
	 * 2번방식으로 구현
	 */
	
	// value에 있는 {변수명의 값}이 PathVariable의 이름으로 넘어가서 매개변수 이름으로 들어간다
	@RequestMapping(value="/file_down/{img_seq}", method=RequestMethod.GET)
	public String file_down(@PathVariable("img_seq")String img_seq,
			HttpServletRequest req, HttpServletResponse res) {
		// 1. img_file_seq 값으로 다운로드를 수행할 파일정보를 DB로부터 추출
		ImageFilesVO imgFVO = ifDao.findBySeq(Long.valueOf(img_seq));
		// 2. 서버에 실제로 저장된 파일 이름(UUID+파일이름) 긁어오기
		String downFileName = imgFVO.getImg_file_upload_name();
		// 3. 파일이름과 서버의 저장된 path 정보를 연결해서 파일 정보로 받기
		File downFile = fService.find_down(downFileName);
		if(downFile == null) return "NOT_FOUND"; // 파일이 없으면 ajax에 NOT_FOUND 문자열을 ret
		
		String originName = imgFVO.getImg_file_origin_name(); // 업로드 전 원래 이름으로 다운로드 수행하도록 세팅
		if(originName == null || originName.equals(" ")) { // 이름이 없거나 공백이면
			originName = "noname.file"; // 에러가 날 수 있으니 임의로 지정
		}
		
		String browser = req.getHeader("User-Agent"); // 요청을 하고 있는 browser가 무엇인가
		

			try {
				// 문자열 중에 MSIE, Chrome, Trident가 있으면
				if(browser.contains("MSIE") || browser.contains("Chrome") || browser.contains("Trident")) {
					originName = URLEncoder.encode(originName, "UTF-8").replaceAll("\\+", "%20");
				}
				else { // 해당하는 브라우저들이 아니면 String 객체를 이용해서 처리
					originName = new String(originName.getBytes("UTF-8"), "ISO-8850-1");
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				log.debug("파일 인코딩 오류 발생");
		}
		
		// 4. response에 파일을 싣기 위해 설정
		res.setContentType("application/octer-stream");
		res.setHeader("Content-Transfer-Encoding", "binary;");
		// 5. 파일을 보낼 때 원래 이름으로 보이도록 만들기. attachment 사용
		originName = String.format("attachment;filename=%s", originName);
		res.setHeader("Content-Disposition", originName);

		try {
			// 6. Stream을 보낼 통로 개방
			OutputStream os = res.getOutputStream();
			// 7. 서버에 저장된 파일을 읽어오기
			FileInputStream fs = new FileInputStream(downFile);
			
			int nCount = 0;
			byte[] sendData = new byte[512]; // 한번에 보낼 데이터의 크기지정. 네트워크 환경이 좋지 않으면 더 작게
			while(true) {
				nCount = fs.read(sendData); // 지정된 크기로 파일을 읽어서 몇번을 읽었는지 변수에 지정
				if(nCount == -1) break;
				os.write(sendData, 0, nCount); // 0~nCount까지 자료값들을 *512해서 받음. 흐름제어를 신경안써도 잘 된다 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.debug("다운로드 중 오류 발생");
		}
		return "OK";
	}
	
	@RequestMapping(value="/image_delete",method=RequestMethod.POST)
	public String img_delete(
			@RequestParam("img_id") String img_id) {
		
		try {
			
			ImageFilesVO imgVO = ifService.findBySeq(img_id); 
			fService.file_delete(imgVO.getImg_file_upload_name());
			ifService.img_file_delete(img_id);
			
		} catch (Exception e) {
			// TODO: handle exception
			return "FAIL";
		}
		return "OK";
	}

	@RequestMapping(value="/main_image", method=RequestMethod.POST)
	public String main_image(@RequestParam("img_pcode") String img_seq, @RequestParam("img_file") String img_file) {
		ImageVO imageVO = imgDao.findBySeq(img_seq);
		imageVO.setImg_file(img_file); // 대표이미지에 세팅
		int ret = imgDao.update(imageVO);
		return ret + "";
	}
}
