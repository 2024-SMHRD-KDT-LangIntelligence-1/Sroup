package com.smhrd.sroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.smhrd.sroup.entity.UserEntity;
import com.smhrd.sroup.repository.UserRepo;

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
	public String login(String user_id, String user_pw, HttpSession session) {
		System.out.println("user_id :" + user_id);
		
		UserEntity enti = repo.findByUser_idAndUser_pw(user_id,user_pw);
		
		System.out.println("user_id : " + enti.getUser_id());
		System.out.println("user_pw : " + enti.getUser_pw());
		
		
		session.setAttribute("user", enti);
		return "redirect:/";
	}

	
	
}
