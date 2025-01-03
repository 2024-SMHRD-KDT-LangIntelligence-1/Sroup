package com.smhrd.sroup.controller;


import java.security.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.sroup.mapper.TimerMapper;
import com.smhrd.sroup.model.TimerVO;
import com.smhrd.sroup.model.UserVO;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.websocket.Session;


@RestController
public class TimerController {
	
	@Autowired
	TimerMapper tmapper;
	
	@Transactional
	@PostMapping("/api/timer/save")
	public ResponseEntity<String> timeData(@RequestBody TimerVO timervo,
			HttpSession session) {
		
		// 타임스탬프 값 받기 (Timestamp 객체로 직접 받기)
        java.sql.Timestamp startTime = timervo.getSt_tm();  
        java.sql.Timestamp endTime = timervo.getEd_tm();    

        // 시간 계산 (Duration 사용)
        Duration duration = Duration.between(startTime.toInstant(), endTime.toInstant());
        
        // Duration에서 시간과 분 추출
        long hours = duration.toHours(); // 총 시간
        long minutes = duration.toMinutes() % 60; // 남은 분

        // 간격을 HH시간 MM분 형태로 표현
        String durationString = String.format("%d시간 %d분", hours, minutes);
        
        UserVO user = (UserVO) session.getAttribute("user");
      
        if(user != null) {
        	// VO 객체에 값 설정
            timervo.setUser_id(user.getUser_id());
            timervo.setDuration(durationString);
        } else {
        	System.out.println("로그인되지 않은 상태입니다.");
        }
        
        System.out.println("시작 시간: " + startTime);
        System.out.println("종료 시간: " + endTime);
        System.out.println("걸린 시간: " + durationString);
        
        // DB 저장
        tmapper.StudyTimerMapper(timervo);

        return ResponseEntity.ok("타이머 기록이 성공적으로 저장되었습니다.");

	}
}
