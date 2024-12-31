package com.smhrd.sroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhrd.sroup.mapper.UserMapper;
import com.smhrd.sroup.model.UserVO;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	@Autowired
	UserMapper userMapper;

	// 메인화면
	// TODO 1. 로그인 한 상태, 2. 안 한 상태로 구분지어야 함.
	@GetMapping("/")
	public String home(HttpSession session) {
		return "3-1.main-out";
	}

	// 메인화면(로그인 X) -> 로그인 화면
	@GetMapping("/login")
	public String login() {
		return "2.login";
	}

	// 메인화면(로그인 X) -> 회원가입 화면
	@GetMapping("/signup")
	public String signup() {
		return "1.signup";
	}

	// 메인화면(로그인 O) -> 그룹 생성 화면
	@GetMapping("/groupgenerate")
	public String groupGenerate() {
		return "4.groupgenerate";
	}

	// 메인화면(로그인 O) -> 마이 페이지 화면
	// 사이드바 (회원정보 수정 클릭) -> 회원정보 수정 화면
	@GetMapping("/edit-profile")
	public String editProfile(HttpSession session) {
		System.out.println("=============================");
		System.out.println(session.getAttribute("user"));
		System.out.println("=============================");
		return "edit-profile";
	}

	// 사이드바 (이력 조회 클릭) -> 이력 조회 화면
	@GetMapping("/history")
	public String history() {
		return "history";
	}

	// 사이드바 (이력 조회 클릭) -> 이력 조회 화면
	@GetMapping("/mystudy")
	public String mystudy() {
		return "mystudy";
	}

}
