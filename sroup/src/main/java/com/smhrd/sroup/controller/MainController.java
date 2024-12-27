package com.smhrd.sroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String home() {
		return "3-1.main-out";
	}
	
	@RequestMapping(value="/history.html")
	public String history() {
		return "history";
	}
	
	@RequestMapping(value="/mystudy.html")
	public String study() {
		return "mystudy";
	}
	
}
