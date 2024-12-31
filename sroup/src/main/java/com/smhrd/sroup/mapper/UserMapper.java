package com.smhrd.sroup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.sroup.model.UserVO;

@Mapper
public interface UserMapper {

	UserVO login(UserVO vo);
	
	// 내가 가입한 스터디 목록 조회
    List<UserVO> selectJoinedStudies(String user_id);

}