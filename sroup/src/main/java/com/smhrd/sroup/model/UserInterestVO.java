package com.smhrd.sroup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInterestVO {

	private String user_id;	// 이메일
	private int intr_cd;	// 관심분야 코드
}
