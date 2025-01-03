package com.smhrd.sroup.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.sroup.model.TimerVO;

@Mapper
public interface TimerMapper {
	// TimerVO 객체를 DB에 삽입하는 메서드
	void StudyTimerMapper (TimerVO timervo);

}
