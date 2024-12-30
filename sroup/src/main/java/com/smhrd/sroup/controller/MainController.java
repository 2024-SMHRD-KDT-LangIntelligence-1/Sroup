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

	@GetMapping("/")
	public String home() {
		return "3-1.main-out";
	}

	// 화면 전환 GET 방식 start ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
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
	public String editProfile() {
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
	
	//
	// 화면 전환 GET 방식 end ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
	
	
	// 화면 전환 POST 방식 start ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 로그인 기능
	@PostMapping("/login.do")
	public String login_do(@RequestParam ("user_id") String userId, 
			@RequestParam ("user_pw") String userPw, HttpSession session, Model model) {
		UserVO userVO = new UserVO();
		userVO.setUser_id(userId);
		userVO.setUser_pw(userPw);
		
		UserVO vo = userMapper.login(userVO);
		
		System.out.println(vo.getUser_id());
	    // 로그인 성공 여부 확인
	    if (vo != null) {
	    	// 세션에 사용자 정보 저장
	        session.setAttribute("user", vo);
	        return "3.main-in";		// 로그인 성공 후 3.main-in.html로 이동 
	        //return "redirect:/"; // 로그인 성공 후 메인 페이지로 리다이렉트
	    } else {
	    	// 로그인 실패 시 에러 메세지 전달
	        model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
	        return "2.login"; // 로그인 페이지로 다시 이동
	    }

		 //return "redirect:/";
	}

	// 회원가입 기능
	// TODO 기능 구현하기
	@PostMapping("/signup.do")
	public String signup_do() {
		
		return "";
	}
	
	
	//
	// 화면 전환 POST 방식 end ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
}
