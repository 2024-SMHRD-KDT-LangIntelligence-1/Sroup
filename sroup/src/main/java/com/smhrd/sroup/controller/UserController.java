package com.smhrd.sroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhrd.sroup.mapper.UserMapper;
import com.smhrd.sroup.model.UserVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	UserMapper usermapper;
	// 로그인 기능
	@PostMapping("/login.do")
	public String login_do(@RequestParam ("user_id") String userId, 
			@RequestParam ("user_pw") String userPw, HttpSession session, Model model) {
		UserVO userVO = new UserVO();
		userVO.setUser_id(userId);
		userVO.setUser_pw(userPw);
		System.out.println(userVO.getUser_id());
		System.out.println(userVO.getUser_pw());
		
		System.out.println(usermapper.login(userVO));
		
		//여기 
		UserVO vo = usermapper.login(userVO);
		
//		System.out.println("vo임"+vo.getUser_id());
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

	// 마이페이지 - 정보 수정 처리
	@PostMapping("/edit-profile.do")
	public String editProfile_do(HttpSession session) {
		System.out.println("=============================");
		System.out.println(session.getAttribute("user"));
		System.out.println("=============================");
		
		return "";
	}
	
	
	// 회원가입 기능
	// TODO 기능 구현하기
	@PostMapping("/signup.do")
	public String signup_do() {
		
		return "";
	}
	
}
