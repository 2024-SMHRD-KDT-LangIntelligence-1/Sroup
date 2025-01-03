package com.smhrd.sroup.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimerVO {
	
	private String user_id;
	private Timestamp st_tm;
	private Timestamp ed_tm;
	private String duration; 

	
}
