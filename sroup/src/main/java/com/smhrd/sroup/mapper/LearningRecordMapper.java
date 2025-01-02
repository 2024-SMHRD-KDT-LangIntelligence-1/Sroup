package com.smhrd.sroup.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.sroup.model.LearningRecordVO;

@Mapper
public interface LearningRecordMapper {

	void createLearningRecord(LearningRecordVO vo);
	
}
