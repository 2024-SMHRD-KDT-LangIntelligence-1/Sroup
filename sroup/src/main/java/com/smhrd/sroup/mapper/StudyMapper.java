package com.smhrd.sroup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
	
	// 상세 페이지 관련 스터디 데이터 가져오기 (1건, study_cd로)
	StudyVO getStudyByCd(int studyCd);

	// 특정 사용자(userId)가 JOINED 상태인 스터디 목록 가져오기
    List<StudyVO> getJoinedStudies(@Param("userId") String userId);
    @Select("SELECT * FROM tb_study WHERE study_title = #{study_title} limit 1;")
	StudyVO getStudyByTitle(String studyTitle);
    
}
