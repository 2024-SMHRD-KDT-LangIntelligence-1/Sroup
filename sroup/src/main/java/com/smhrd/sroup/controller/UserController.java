package com.smhrd.sroup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhrd.sroup.mapper.UserMapper;
import com.smhrd.sroup.model.UserVO;
import com.smhrd.sroup.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	// Service 클래스 선언 (DB 처리)
	private UserService userService;

	// 생성자 주입
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	UserMapper usermapper;

	// 로그인 기능
	@PostMapping("/login.do")
	public String login_do(@RequestParam("user_id") String userId, @RequestParam("user_pw") String userPw,
			HttpSession session, Model model) {
		UserVO userVO = new UserVO();
		userVO.setUser_id(userId);
		userVO.setUser_pw(userPw);
		System.out.println(userVO.getUser_id());
		System.out.println(userVO.getUser_pw());

		System.out.println(usermapper.login(userVO));

		// 여기
		UserVO vo = usermapper.login(userVO);

		// 로그인 성공 여부 확인
		if (vo != null) {
			// 세션에 사용자 정보 저장
			session.setAttribute("user", vo);

			return "redirect:/login.do"; // MainController로 리다이렉트
		} else {
			// 로그인 실패 시 에러 메세지 전달
			model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
			return "2.login"; // 로그인 페이지로 다시 이동
		}

	}

	// 로그아웃 처리
	@PostMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate(); // 세션 무효화

		return "redirect:/"; // 3-1.main-out.html과 매핑된 경로로 이동.
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
	@PostMapping("/signup.do")
	public String signup_do(@RequestParam("user_name") String name, @RequestParam("user_phone") String phone,
			@RequestParam("user_id") String email, @RequestParam("user_pw") String password,
			@RequestParam("interest") List<String> interests, @RequestParam("personality") List<String> personalities,
			Model model) {

		// DB 저장
		try {
			userService.userSignUp(name, phone, email, password, interests, personalities);

			model.addAttribute("message", "회원가입이 완료되었습니다!");

			return "2.login"; // 성공 시 로그인 페이지로 이동
		} catch (Exception e) {
			model.addAttribute("error", "회원가입 중 문제가 발생했습니다.");
			return "1.signup"; // 실패 시 회원가입 페이지로 이동
		}
	}
}
