package com.smhrd.sroup.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

	private String user_id; // Primary Key
	private String user_pw;
	private String user_name;
	private String user_phone;
	private String user_profile_img;
	private Timestamp joined_at;
}
