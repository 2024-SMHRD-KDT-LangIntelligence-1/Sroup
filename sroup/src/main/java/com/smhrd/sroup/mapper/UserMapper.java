package com.smhrd.sroup.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.sroup.model.UserVO;

@Mapper
public interface UserMapper {

	UserVO login(UserVO vo);

	// 메소드 추가
	// 이메일과 비번으로 회원 가입했는지 조회하기

}