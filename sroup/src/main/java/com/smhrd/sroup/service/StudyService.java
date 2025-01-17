package com.smhrd.sroup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.sroup.mapper.LearningRecordMapper;
import com.smhrd.sroup.mapper.StudyMapper;
import com.smhrd.sroup.model.StudyVO;

@Service
public class StudyService {
    @Autowired
    private StudyMapper studyMapper;

    @Autowired
    private LearningRecordMapper lrMapper;   
    
 // 내가 가입한 스터디 가져오기
    public List<StudyVO> getJoinedStudies(String userId) {
        return studyMapper.getJoinedStudies(userId);
    }
    
    // 회고록 데이터 가져오기
	public String getStudyContentByDate(String user_id, int joined_study_cd, String date) {
		
		return lrMapper.getOneLearningRecord(user_id, joined_study_cd, date);
	}
    
}

