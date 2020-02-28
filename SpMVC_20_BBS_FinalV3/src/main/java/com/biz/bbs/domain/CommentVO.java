package com.biz.bbs.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Alias("commentVO")
public class CommentVO {
	private long c_id;//	NUMBER	PRIMARY KEY,
	private long c_p_id;//	NUMBER	NOT NULL,
	private long c_b_id;
	private String c_date_time;//	VARCHAR2(30)	NOT NULL,
	private String c_writer;//	nVARCHAR2(30)	NOT NULL,
	private String c_subject;//	nVARCHAR2(125)	NOT NULL
}
