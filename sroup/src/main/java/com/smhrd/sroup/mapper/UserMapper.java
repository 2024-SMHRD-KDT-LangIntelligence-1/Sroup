package com.smhrd.sroup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.sroup.model.UserVO;

@Mapper
public interface UserMapper {

	// 로그인 처리
	UserVO login(UserVO uservo);
	
    // 회원 가입 처리
	void createAccount(UserVO vo);
	
	// 내가 가입한 스터디 목록 조회
    List<UserVO> selectJoinedStudies(String user_id);
    

    // 내가 가입한 스터디 조회 (마이 페이지 - 마이 보드)
    // 로그인 한 회원이 현재 가입되어 있는 스터디 중, 가장 최근에 가입한 스터디 1건의 study_cd 정보 취득
    // (tb_study_member의 status가 'JOINED'인 study_cd)
    int selectJoinedStudyCdByEmail(String user_id);
    
    // 마이 페이지 화면 - 회원정보 수정
    // 로그인한 회원의 정보 취득 (user_id, user_pw, user_name, user_phone, user_profile_img)
    UserVO selectLoginUser(String user_id); 

}