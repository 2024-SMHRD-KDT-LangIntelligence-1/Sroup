package com.smhrd.sroup.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smhrd.sroup.mapper.UserMapper;
import com.smhrd.sroup.model.StudyVO;
import com.smhrd.sroup.model.UserVO;
import com.smhrd.sroup.service.StudyService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudyController {

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private StudyService studyService;
    
    
//    @GetMapping("/study/{studyCd}")
//    public String getStudyDetails(@PathVariable("studyCd") int studyCd, Model model) {
//        // studyCd로 스터디 정보 조회
//        StudyVO study = studyService.getStudyById(studyCd);
//        model.addAttribute("study", study);
//
//        return "5.groupinvolve"; // templates/5.groupinvolve.html 파일을 렌더링
//    }
    
    @GetMapping("/my-studies")
    public String myStudies(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("user");
        if (userId == null) {
            return "redirect:/login"; // 로그인하지 않은 경우
        }

        // 내가 가입한 스터디 목록 조회
        List<StudyVO> joinedStudies = studyService.getJoinedStudies(userId);

        // joinedStudies 로그 출력
        System.out.println("joinedStudies: " + joinedStudies);

        model.addAttribute("joinedStudies", joinedStudies);

        return "3-1.main-out"; // 스터디 목록 페이지 렌더링
    }
   


// // 스터디 상세 페이지 이동
//    @GetMapping("/study/{id}")
//    public String studyDetails(@PathVariable("id") int id, Model model) {
//        // 데이터베이스에서 스터디 데이터를 가져옴
//        StudyVO study = studyService.getStudyById(id); // Service와 Mapper에서 데이터를 가져옴
//        if (study == null) {
//            return "redirect:/studies"; // 데이터가 없는 경우 목록으로 리디렉션
//        }
//        model.addAttribute("study", study);
//
//        // 댓글 예시 데이터 추가
//        List<String> comments = List.of("첫 번째 댓글", "두 번째 댓글");
//        model.addAttribute("comments", comments);
//
//        return "5.groupinvolve"; // 템플릿 반환
//    }
    

    // 생성자 주입
    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }
    
//    @GetMapping("/study/list")
//    public String getStudyList(Model model, HttpSession session) {
//        // 사용자 ID 가져오기
//        String userId = (String) session.getAttribute("user");
//
////        // 추천 스터디
////        List<StudyVO> recommendedStudies = studyService.getRecommendedStudies();
////        model.addAttribute("recommendedStudies", recommendedStudies);
////
////        // 인기 스터디
////        List<StudyVO> popularStudies = studyService.getPopularStudies();
////        model.addAttribute("popularStudies", popularStudies);
//
//        // 내가 가입한 스터디
//        if (userId != null) {
//            List<StudyVO> joinedStudies = studyService.getJoinedStudies(userId);
//            model.addAttribute("joinedStudies", joinedStudies);
//        }
//
//        return "3-1.main-out"; // 목록 페이지 템플릿 반환
//    }

   

    // 댓글 등록 처리
    @PostMapping("/api/comments")
    @ResponseBody
    public String addComment(@RequestBody String comment) {
        // 여기서 댓글 저장 로직 추가 가능
        System.out.println("새 댓글: " + comment);
        return "댓글 등록 성공";
    }
}

