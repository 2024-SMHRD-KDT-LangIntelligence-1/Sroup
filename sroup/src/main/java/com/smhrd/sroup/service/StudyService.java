package com.smhrd.sroup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.sroup.mapper.StudyMapper;
import com.smhrd.sroup.model.StudyVO;

@Service
public class StudyService {
    @Autowired
    private StudyMapper studyMapper;

//    // 추천 스터디 가져오기
//    public List<StudyVO> getRecommendedStudies() {
//        return studyMapper.getRecommendedStudies();
//    }
//
//    // 인기 스터디 가져오기
//    public List<StudyVO> getPopularStudies() {
//        return studyMapper.getPopularStudies();
//    }
    
 // 내가 가입한 스터디 가져오기
    public List<StudyVO> getJoinedStudies(String userId) {
        return studyMapper.selectJoinedStudies(userId);
    }
    
}

