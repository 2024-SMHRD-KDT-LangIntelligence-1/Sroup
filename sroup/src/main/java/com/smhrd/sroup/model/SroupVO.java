package com.smhrd.sroup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SroupVO {
	
	private String user_id;
	private String user_pw;
	private String uesr_name;
	private String user_phone;
	private String user_profile_img;
	private String joined_at;

}
