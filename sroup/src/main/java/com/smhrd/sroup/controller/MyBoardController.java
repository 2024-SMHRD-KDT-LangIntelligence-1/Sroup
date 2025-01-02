package com.smhrd.sroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhrd.sroup.mapper.LearningRecordMapper;
import com.smhrd.sroup.model.LearningRecordVO;
import com.smhrd.sroup.model.UserVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyBoardController {

	@Autowired
	LearningRecordMapper lrMapper;
	
	// 회고록 등록
	@PostMapping("/write-learning-record")
	public String writeLearningRecord(@RequestParam("learning_title") String learning_title,
			@RequestParam("learning_content") String learning_content,
			HttpSession session) {

		System.out.println("=================================");
		System.out.println("writeLearningRecord 메소드 진입");
		System.out.println("=================================");
		
		// HttpSession에서 "user"라는 키로 저장된 값을 가져옴
		UserVO user = (UserVO) session.getAttribute("user");
		
		// 값이 null인지 확인 (로그인 여부 확인)
		if (user != null) {
		    System.out.println("로그인 유저 아이디: " + user.getUser_id());
		    System.out.println("가입한 스터디 코드: " + session.getAttribute("joined_study_cd"));
		} else {
		    System.out.println("로그인되지 않은 상태입니다.");
		}
		
		// 회고록 값 세팅
		LearningRecordVO lrVO = new LearningRecordVO();
		lrVO.setLearning_title(learning_title);
		lrVO.setLearning_content(learning_content);
		lrVO.setUser_id(user.getUser_id());
		lrVO.setStudy_cd((Integer)session.getAttribute("joined_study_cd"));
		
		System.out.println(lrVO);
		
		// 회로록 DB 등록 처리
		lrMapper.createLearningRecord(lrVO);

		return "mystudy";
	}

}
