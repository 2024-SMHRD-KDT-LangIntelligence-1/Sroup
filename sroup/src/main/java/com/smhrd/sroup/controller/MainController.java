package com.smhrd.sroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhrd.sroup.entity.UserEntity;
import com.smhrd.sroup.model.UserVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	@GetMapping("/")
	public String home(@RequestParam(value = "action", required = false) String action) {

		if ("login".equals(action)) {
			// 로그인 페이지로 이동
			return "2.login"; // 2.login.html 파일 반환
		} else if ("signup".equals(action)) {
			return "1.signup"; // 1.signup.html 파일 반환
		}

		// 기본적으로 메인 화면 반환
		return "3-1.main-out"; // 3-1.main-out.html 파일 반환
	}

	@PostMapping("/history.html")
	public String history() {
		return "history";
	}

	@PostMapping("/mystudy.html")
	public String study() {
		return "mystudy";
	}
}
