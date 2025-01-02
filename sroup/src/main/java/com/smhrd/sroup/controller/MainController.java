package com.smhrd.sroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping("/")
	public String home(HttpSession session) {
		// 세션에서 로그인 여부 확인
		if (session.getAttribute("user") != null) {
			return "3-1.main-in"; // 로그인한 상태
		}
		return "3-1.main-out"; // 로그인하지 않은 상태
	}

	// 로그인 화면
	@GetMapping("/login")
	public String login() {
		return "2.login";
	}

	// 회원가입 화면
	@GetMapping("/signup")
	public String signup() {
		return "1.signup";
	}

	// 그룹 생성 화면
	@GetMapping("/groupgenerate")
	public String groupGenerate() {
		return "4.groupgenerate";
	}

	// 마이 페이지 화면 - 회원정보 수정 - 회원정보 표시
	@GetMapping("/edit-profile")
	public String editProfile(HttpSession session, Model model) {
		
		// HttpSession에서 "user"라는 키로 저장된 값을 가져옴
		UserVO user = (UserVO) session.getAttribute("user");

		// 로그인 여부 확인
		if (user != null) {
			System.out.println("유저 아이디: " + user.getUser_id());
		} else {
			System.out.println("로그인되지 않은 상태입니다.");
		}

		if (session.getAttribute("user") == null) {
			return "redirect:/login"; // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
		}

		// DB에서 유저 정보 가져오기(세션에서 넘겨받은 id 값으로 tb_user에서 select)
		// user_id, user_pw, user_name, user_phone, user_profile_img
		UserVO selectedUserVO =  userMapper.selectLoginUser(user.getUser_id());
		System.out.println("selectedUserVO: " + selectedUserVO);
		
		// 뷰로 데이터 전달
	    model.addAttribute("user", selectedUserVO);

		return "edit-profile";
	}

	// 이력 조회 화면
	@GetMapping("/history")
	public String history(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login"; // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
		}
		return "history";
	}

	// 내 스터디 화면
	@GetMapping("/mystudy")
	public String mystudy(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login"; // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
		}
		
		// HttpSession에서 "user"라는 키로 저장된 값을 가져옴
		UserVO user = (UserVO) session.getAttribute("user");
		
		// 로그인 여부 확인
		if (user != null) {
		    System.out.println("유저 아이디: " + user.getUser_id());
		} else {
		    System.out.println("로그인되지 않은 상태입니다.");
		}
		
		// 유저가 가입한 스터디 중 하나(가장 최근에 가입한 study_cd) 취득
		int joined_study_cd = userMapper.selectJoinedStudyCdByEmail(user.getUser_id());
		// 가입한 study_cd를 세션에 등록
		session.setAttribute("joined_study_cd", joined_study_cd);
		
		System.out.println("joined_study_cd: " + joined_study_cd);
		
		return "mystudy";
	}

	// 스터디 참여 화면
	@GetMapping("/groupinvolve")
	public String groupInvolve(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		return "5.groupinvolve";
	}

	// 내가 가입한 스터디 화면
	@GetMapping("/joingroup")
	public String joinGroup(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		return "joingroup";
	}

	// 인기 스터디 화면
	@GetMapping("/popularity")
	public String popularity(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		return "popularity";
	}

	// 프로젝트 관련 화면
	@GetMapping("/projectgroup")
	public String projectGroup(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		return "projectgroup";
	}

	// 추천 스터디 화면
	@GetMapping("/recommend")
	public String recommend(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		return "recommend";
	}

}
