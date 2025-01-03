package com.smhrd.sroup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.sroup.model.StudyVO;

@Mapper
public interface StudyMapper {


	
//    // 추천 스터디 목록 조회
//    List<StudyVO> getRecommendedStudies();
//
//    // 인기 스터디 목록 조회
//    List<StudyVO> getPopularStudies();

    // 내가 가입한 스터디 목록 조회
//    List<StudyVO> selectJoinedStudies(String userId);
//
//	StudyVO selectStudyById(int studyCd);
	List<StudyVO> selectJoinedStudies(String userId);
	
	// 인기 스터디 조회(1건)
	StudyVO getPopularStudyByStudyCd(int study_cd);

}