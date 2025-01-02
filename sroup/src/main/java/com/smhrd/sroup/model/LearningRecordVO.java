package com.smhrd.sroup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LearningRecordVO {

	private String learning_title;
	private String learning_content;
	private String learning_file;
	private String user_id;
	private int study_cd;
}
