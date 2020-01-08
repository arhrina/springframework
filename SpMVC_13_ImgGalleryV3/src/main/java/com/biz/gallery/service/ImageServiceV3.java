package com.biz.gallery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.gallery.domain.ImageFilesVO;
import com.biz.gallery.domain.ImageVO;
import com.biz.gallery.repository.ImageDao;

@Service
public class ImageServiceV3 extends ImageServiceV2 {
	
	@Autowired
	public ImageServiceV3(ImageDao imDao, ImageFileService ifService, FileService fService) {
		super(imDao, ifService, fService);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int insert(ImageVO imageVO) {
		
		/* 
		 * List<ImageFilesVO> imgFiles = imageVO.getImg_files(); if(imgFiles != null &&
		 * imgFiles.size() > 0) {
		 * imageVO.setImg_file(imgFiles.get(0).getImg_file_upload_name()); // 대표이미지 교체
		 * for(ImageFilesVO ifVO : imgFiles) {
		 * ifVO.setImg_file_origin_name(ifVO.getImg_file_upload_name().substring(36)); }
		 * }
		 */
		imageVO = this.updateImageFiles(imageVO);
		int ret = imDao.insert(imageVO);
		long img_pcode = imageVO.getImg_seq();
		ifService.imageFileInsert(imageVO.getImg_up_files(),img_pcode);
	
		return ret;
	}

	@Override
	public int update(ImageVO imageVO) {
		// TODO Auto-generated method stub
		imageVO = this.updateImageFiles(imageVO);
		int ret = imDao.update(imageVO);
		long img_pcode = imageVO.getImg_seq();
		ifService.imageFileInsert(imageVO.getImg_up_files(), img_pcode);
		return ret;
	}
	
	// 접근제한자(scope). public : 누구나 사용, private : 현재 클래스만 사용, protected : 현재 클래스와 상속받은 클래스만 사용
	
	/* 
	 * service, serviceV2에는 없는 새로운 메서드이고 현재는 V3에서만 사용되는데,
	 * 만약 V3를 상속받아서 새로운 클래스를 만들 경우엔 사용할 수 있도록 protected
	 */
	protected ImageVO updateImageFiles(ImageVO imageVO) { // update랑 겹치므로 잘라서 메서드로 빼내기
		// 대표이미지 교체해서 이름 세팅
		List<ImageFilesVO> imgFiles = imageVO.getImg_up_files();
		if(imgFiles != null && imgFiles.size() > 0) {
			imageVO.setImg_file(imgFiles.get(0).getImg_file_upload_name()); // 대표이미지 교체
			for(ImageFilesVO ifVO : imgFiles) {
				ifVO.setImg_file_origin_name(ifVO.getImg_file_upload_name().substring(36));
			}
		}
		return imageVO;
	}
	
}
