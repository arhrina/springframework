package com.biz.product.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	@Autowired
	ServletContext context; // context는 prj의 경로(path)

	public String fileUp(MultipartFile u_file) {

		String originName = u_file.getOriginalFilename(); // 업로드된 파일 정보에서 파일 이름만을 추출

		// ServletContext는 tomcat server가 작동되고 있는 곳에 대한 정보
		// getRealPath("/")는 tomcat server가 prj를 service할 때 root로 설정하여 여러가지 정보를 저장하고 있는 폴더경로
		String uploadPath = context.getRealPath("/"); // rootPath와 같다
		uploadPath += "files/"; // uploadPath : /product/files/
		if (u_file != null) { // u_file이 null이 아니면. 업로드한 파일이 있다면
			// /files/ 폴더에 대한 IO 정보 추출
			File dir = new File(uploadPath);

			if (!dir.exists()) {// 현재 서버에 /files/라는 폴더가 없다면
				dir.mkdirs(); // 폴더를 생성
			}
			// original 파일이름을 사용하면 해킹 사고를 일으킬 수 있으므로 파일이름에 UUID를 붙여 DB table에 저장한다
			String strUUID = UUID.randomUUID().toString();
			strUUID += originName;
			
			// uploadPath + originName;
			// /product/files/2019.jpg라는 이름으로 파일명을 만들고 해당하는 파일에 대한 정보를 객체로 생성해서 업로드할 준비
			File uploadFile = new File(uploadPath, strUUID);
			try {
				// web에서 upload된 파일(u_file)을 방금 설정한 파일이름(uploadFile)으로 전송
				// web에서 server로 파일이 복사가 된다
				u_file.transferTo(uploadFile);
				// u_file을 uploadFile로 복사(저장)

				return strUUID; // 파일이 정상적으로 업로드되면 originName을 Controller로 보낸다

			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// exception들이 안걸리고 업로드가 안되면 null값을 Controller로 return한다
		return null;
	}
}
