package com.smhrd.sroup.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.sroup.model.LearningRecordVO;

@Mapper
public interface LearningRecordMapper {

	void createLearningRecord(LearningRecordVO vo);
	
	String getOneLearningRecord(String user_id, int study_cd, String created_at);
	
}
