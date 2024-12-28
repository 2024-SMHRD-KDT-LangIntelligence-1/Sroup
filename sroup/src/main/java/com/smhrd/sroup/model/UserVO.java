package com.smhrd.sroup.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

	private String userId;
	private String userPw;
	private String userName;
	private String userPhone;
	private String userProfileImg;
	private Timestamp joinedAt;
}
