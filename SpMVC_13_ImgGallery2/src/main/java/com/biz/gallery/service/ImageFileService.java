package com.biz.gallery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.gallery.domain.ImageFilesVO;
import com.biz.gallery.repository.ImageFileDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImageFileService {
	
	ImageFileDao ifDao;
	
	@Autowired
	public ImageFileService(ImageFileDao ifDao) {
		this.ifDao = ifDao;
	}
	
	public int ImageFileInsert(List<ImageFilesVO> img_files) {
		int ret = 0;
		
		for(ImageFilesVO file: img_files) {
			ret += ifDao.insert(file);
		}
		return ret;
	}
	
	public int ImageFileInsert(List<ImageFilesVO> img_files, long img_p_code) {
		
		
		log.debug("퍼알" + img_files.toString());
		int ret = ifDao.bulk_insert(img_files, 
					img_p_code);
		return ret;
	}

	public ImageFilesVO findBySeq(String img_id) {
		// TODO Auto-generated method stub
		return ifDao.findBySeq(Long.valueOf(img_id));
		
	}

	public int img_file_delete(String img_id) {
		// TODO Auto-generated method stub
		return ifDao.delete(Long.valueOf(img_id));
	}

}
