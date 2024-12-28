package com.smhrd.sroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.smhrd.sroup.entity.UserEntity;
import com.smhrd.sroup.repository.UserRepo;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	@Autowired
	UserRepo repo;
	
	@GetMapping("/")
	public String home() {
		return "3-1.main-out";
	}
	
	@PostMapping("/history.html")
	public String history() {
		return "history";
	}
	
	@PostMapping("/mystudy.html")
	public String study() {
		return "mystudy";
	}
	
	//로그인 기능
	@PostMapping("/2.login.html")
	public String login(String user_id, String user_pw, HttpSession session, Model model) {
	    UserEntity user = repo.findByUser_idAndUser_pw(user_id, user_pw);
	    
	    // 로그인 성공 여부 확인
	    if (user != null) {
	    	// 세션에 사용자 정보 저장
	        session.setAttribute("user", user);
	        return "redirect:/"; // 로그인 성공 후 메인 페이지로 리다이렉트
	    } else {
	    	// 로그인 실패 시 에러 메세지 전달
	        model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
	        return "2.login"; // 로그인 페이지로 다시 이동
	    }
	}


	
	
}
