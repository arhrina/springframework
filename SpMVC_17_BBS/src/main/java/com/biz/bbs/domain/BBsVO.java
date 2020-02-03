package com.biz.bbs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class BBsVO {
	private long bbs_id;//	bigint	auto_increment	primary key
	private String bbs_p_id;//	bigint	default 0	
	private String bbs_writer;//	varchar(50)	not null	
	private String bbs_date;//	varchar(10)		
	private String bbs_time;//	varchar(10)		
	private String bbs_subject;//	varchar(125)		
	private String bbs_content;//	varchar(1000)		
	private int bbs_count;//	int	default 0	
}
