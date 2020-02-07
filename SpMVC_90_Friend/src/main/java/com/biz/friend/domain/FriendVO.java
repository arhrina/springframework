package com.biz.friend.domain;

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

public class FriendVO {
	private long f_id;// BIGINT primary key AUTO_INCREMENT,
	private String f_name;// VARCHAR(20),
	private String f_tel;// VARCHAR(11),
	private String f_addr;// VARCHAR(100),
	private String f_hobby;// VARCHAR(20),
	private String f_relat;// VARCHAR(20)
}
