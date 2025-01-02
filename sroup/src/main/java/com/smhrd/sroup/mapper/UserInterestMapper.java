package com.smhrd.sroup.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.sroup.model.UserInterestVO;

@Mapper
public interface UserInterestMapper {
	
	void createUserInterest(UserInterestVO vo);
	
	UserInterestVO findByUserIdAndIntrCd(UserInterestVO vo);
}