package com.smhrd.sroup.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smhrd.sroup.mapper.StudyMapper;
import com.smhrd.sroup.mapper.StudyScrapMapper;
import com.smhrd.sroup.mapper.UserMapper;
import com.smhrd.sroup.model.StudyVO;
import com.smhrd.sroup.model.UserVO;
import com.smhrd.sroup.service.StudyService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	@GetMapping("/3-1.main-out")
    public String mainOutPage() {
        return "3-1.main-out";
	}
	
	
	@GetMapping("/3.main-in")
    public String mainInPage() {
        return "3.main-in"; // 3.main-in.html을 렌더링
    }
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	StudyScrapMapper studyScrapMapper;
	
	 @Autowired
	 StudyService studyService;
	
	@Autowired
	StudyMapper studyMapper;

	// 메인화면
	@GetMapping("/")
	public String home(HttpSession session, Model model) {
		
		showPopularStudies(model);
		
		// 세션에서 로그인 여부 확인
		if (session.getAttribute("user") != null) {
			showMyStudies(model,session);
			return "3.main-in"; // 로그인한 상태
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
	public String groupGeneratePage(HttpSession session) {
	    if (session.getAttribute("user") == null) {
	        return "redirect:/3-1.main-out"; // 로그인하지 않은 경우 3-1.main-out.html로 리다이렉트
	    }
	    return "4.groupgenerate"; // 로그인한 경우 4.groupgenerate.html 렌더링
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
	public String historyPage(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login"; // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
		}
		return "history";
	}

	// 내 스터디 화면
	@GetMapping("/mystudy")
	public String mystudyPage(HttpSession session) {
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
	@GetMapping("/5.groupinvolve")
	public String groupInvolvePage(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		return "5.groupinvolve";
	}

//	// 내가 가입한 스터디 화면
//	@GetMapping("/joingroup")
//	public String joinGroupPage(HttpSession session) {
//		if (session.getAttribute("user") == null) {
//			return "redirect:/login";
//		}
//		return "joingroup";
//	}

	// 인기 스터디 화면
	@GetMapping("/popularity")
	public String popularityPage(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		return "popularity";
	}

	// 프로젝트 관련 화면
	@GetMapping("/projectgroup")
	public String projectGroupPage(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		return "projectgroup";
	}

	// 추천 스터디 화면
	@GetMapping("/recommend")
	public String recommendPage(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		return "recommend";
	}
	
	// 메인 페이지 표시 (로그인 후) 
	@GetMapping("/login.do")
	public String showMainPage(HttpSession session, Model model) {

		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		
		showMyStudies(model,session);
		showPopularStudies(model);
		
		
		
		return "3.main-in";
	}
	
	// 각 인기 스터디 클릭 시 이동 (로그인 후 메인 페이지)
	@GetMapping("/study/{studyCd}")
	public String showStudyDetail(@PathVariable("studyCd") int studyCd, HttpSession session, Model model) {
	    
		// 세션에서 로그인 여부 확인
		if (session.getAttribute("user") == null) {
			return "2.login"; // 로그인 페이지로 이동
		}
		
		// studyCd를 기반으로 스터디 정보 조회
	    StudyVO study = studyMapper.getStudyByCd(studyCd);

	    System.out.println("==============================");
	    System.out.println("StudyTitle: " + study.getStudyTitle());
	    System.out.println("StudyContent: " + study.getStudyContent());
	    System.out.println("UserId: " + study.getUserId());
	    System.out.println("CurrentMembers: " + study.getCurrentMembers());
	    System.out.println("StudyLimit: " + study.getStudyLimit());
	    System.out.println("StudyStatus: " + study.getStudyStatus());
	    System.out.println("CreatedAt : " + study.getCreatedAt());
	    System.out.println("ClosedAt :" + study.getClosedAt());
	    System.out.println("==============================");
	    if (study == null) {
	        return "redirect:/error"; // 스터디가 없을 경우 에러 페이지로 이동
	    }

	    model.addAttribute("study", study);
	    return "5.groupinvolve_popular"; // 스터디 상세 정보 템플릿
	}
	
	// 인기 스터디 표시
	void showPopularStudies(Model model) {
	    // Mapper 주입 상태 확인
	    if (studyScrapMapper == null) {
	        System.out.println("StudyScrapMapper is null. Check configuration!");
	    } else {
	        System.out.println("StudyScrapMapper is properly injected.");
	    }
		
	    // 인기있는 스터디 top5의 study_cd 리스트 취득
		List<Integer> popularStudies5 = studyScrapMapper.getMostPopularStudiesCdTop(5);
		model.addAttribute("popularStudies5", popularStudies5);
		
		// 인기있는 스터디 top5 tb_study 리스트 취득
		List<StudyVO> studies5 = new ArrayList<>();
		for (int i = 0; i < popularStudies5.size(); i++) {
			studies5.add(studyMapper.getPopularStudyByStudyCd(popularStudies5.get(i)));
		}
		
		//  인기있는 스터디 top5 tb_study 리스트 모델에 등록
		model.addAttribute("studies5", studies5);
	}
	
	// 내가 가입한 스터디 표시
	void showMyStudies(Model model, HttpSession session) {
	    // 세션에서 UserVO 객체 가져오기
	    UserVO user = (UserVO) session.getAttribute("user");

	    // 로그인이 되어 있지 않은 경우 처리
	    if (user == null) {
	        System.out.println("User is not logged in. Cannot display joined studies.");
	        model.addAttribute("joinedStudies", new ArrayList<>()); // 빈 리스트 전달
	        return;
	    }

	    // UserVO에서 userId 추출
	    String userId = user.getUser_id();
	    System.out.println("Logged in as user: " + userId);

	    // 사용자가 JOINED 상태인 스터디 가져오기
	    List<StudyVO> joinedStudies = studyMapper.getJoinedStudies(userId);
	    if (joinedStudies == null || joinedStudies.isEmpty()) {
	        System.out.println("No joined studies found for user: " + userId);
	        model.addAttribute("joinedStudies", new ArrayList<>()); // 빈 리스트 전달
	    } else {
	        System.out.println("Joined studies found for user: " + userId);
	        for (StudyVO study : joinedStudies) {
	            System.out.println("StudyTitle: " + study.getStudyTitle());
	        }
	        model.addAttribute("joinedStudies", joinedStudies);
	    }
	}

	@GetMapping("/study1/{num}")
	public String showStudyDetail2(@PathVariable("num") int num, Model model, HttpSession session) {
		
		String title = (String)session.getAttribute("data"+num);
		System.out.println("helloooooooooooooooooooooo" + title);
		
	    // studyTitle을 기반으로 스터디 정보 조회
	    StudyVO study = studyMapper.getStudyByTitle(title);

	    if (study == null) {
	        return "redirect:/error"; // 스터디가 없을 경우 에러 페이지로 이동
	    }

	    model.addAttribute("study", study);
	    return "5.groupinvolve_popular"; // 스터디 상세 페이지 템플릿
	}
	
	// 내가 가입한 스터디 클릭 시 이동
	@GetMapping("/my-study/{studyCd}")
	public String showMyStudyDetail(@PathVariable("studyCd") int studyCd, HttpSession session, Model model) {
	    
	    // 세션에서 로그인 여부 확인
	    UserVO user = (UserVO) session.getAttribute("user");
	    if (user == null) {
	        System.out.println("User is not logged in. Redirecting to login page.");
	        return "2.login"; // 로그인 페이지로 이동
	    }

	    // studyCd를 기반으로 스터디 정보 조회
	    StudyVO study = studyMapper.getStudyByCd(studyCd);
	    if (study == null) {
	        System.out.println("Study not found for studyCd: " + studyCd);
	        return "redirect:/error"; // 스터디가 없을 경우 에러 페이지로 이동
	    }

	    // 조회된 스터디 정보 출력
	    System.out.println("==============================");
	    System.out.println("StudyTitle: " + study.getStudyTitle());
	    System.out.println("StudyContent: " + study.getStudyContent());
	    System.out.println("UserId: " + study.getUserId());
	    System.out.println("CurrentMembers: " + study.getCurrentMembers());
	    System.out.println("StudyLimit: " + study.getStudyLimit());
	    System.out.println("StudyStatus: " + study.getStudyStatus());
	    System.out.println("CreatedAt: " + study.getCreatedAt());
	    System.out.println("ClosedAt: " + study.getClosedAt());
	    System.out.println("==============================");

	    // 모델에 스터디 정보 추가
	    model.addAttribute("study", study);

	    return "5.groupinvolve"; // 스터디 상세 정보 템플릿
	}
	
	// 내가 가입한 스터디 화면
	@GetMapping("/joingroup")
	public String showJoinGroup(Model model, HttpSession session) {
	    UserVO user = (UserVO) session.getAttribute("user");
	    if (user == null) {
	        return "redirect:/login"; // 로그인 페이지로 리다이렉트
	    }

	    // 사용자가 가입한 스터디 가져오기
	    List<StudyVO> joinedStudies = studyService.getJoinedStudies(user.getUser_id());
	    model.addAttribute("joinedStudies", joinedStudies);

	    return "joingroup"; // 템플릿 이름
	}


	
}
