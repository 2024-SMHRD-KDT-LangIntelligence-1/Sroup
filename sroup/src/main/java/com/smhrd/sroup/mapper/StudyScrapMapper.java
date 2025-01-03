package com.smhrd.sroup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudyScrapMapper {

	List<Integer> getMostPopularStudiesCdTop(int topNum);
}
