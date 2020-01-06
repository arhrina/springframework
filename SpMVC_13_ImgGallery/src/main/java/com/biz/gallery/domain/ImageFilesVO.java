package com.biz.gallery.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Alias("fileVO") // XML을 사용할 때 mapper에서 줄이려고 사용한 annotation
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class ImageFilesVO {
	private long img_file_seq;//	number
	private long img_file_p_code;//	number
	private String img_file_origin_name;//	nvarchar2(255 char)
	private String img_file_upload_name;//	nvarchar2(255 char)
}
