package com.smhrd.sroup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.smhrd.sroup.mapper.UserMapper;
import com.smhrd.sroup.model.UserVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudyController {
	
	 @Autowired
	    private UserMapper userMapper;

	// 내가 가입한 스터디 목록 페이지
	    @GetMapping("/my-studies")
	    public String myStudies(HttpSession session, Model model) {
	        // 세션에서 현재 사용자 ID 가져오기
	        String userId = (String) session.getAttribute("user");
	        if (userId == null) {
	            return "redirect:/login"; // 로그인하지 않은 경우 로그인 페이지로 리디렉트
	        }

	        // 내가 가입한 스터디 목록 조회
	        List<UserVO> studies = userMapper.selectJoinedStudies(userId);
	        model.addAttribute("studies", studies);

	        return "joingroup"; // 내가 가입한 스터디 페이지
	    }

		// 스터디 상세 페이지 이동
		@GetMapping("/study/{id}")
		public String studyDetails(@PathVariable("id") String id, Model model) {
			// 스터디 ID를 전달해 HTML에서 표시할 데이터로 활용
			model.addAttribute("studyId", id);
			return "5.groupinvolve"; // 스터디 상세 페이지로 연결
		}

		// 스터디 목록 더보기 페이지로 이동
		@GetMapping("/studies")
		public String studyList() {
			return "recommend"; // 추천 스터디 목록으로 연결
		}

}
