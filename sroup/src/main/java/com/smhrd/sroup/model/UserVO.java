package com.smhrd.sroup.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_phone;
	private String user_profile_img;
	//private Timestamp joinedAt;
	
}
