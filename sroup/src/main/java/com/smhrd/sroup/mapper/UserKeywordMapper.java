package com.smhrd.sroup.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.sroup.model.UserKeywordVO;

@Mapper
public interface UserKeywordMapper {

	void createUserKeyword(UserKeywordVO vo);
}
