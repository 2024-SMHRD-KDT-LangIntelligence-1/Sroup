package com.smhrd.sroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
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
	@PostMapping("/login.html")
	public void login(String user_id, String user_pw, HttpSession session) {
		System.out.println("user_id :" + user_id);
		
		UserEntity enti = repo.findBy
		
		
	}
}
