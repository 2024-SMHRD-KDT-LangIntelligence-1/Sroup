package com.smhrd.sroup.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.sroup.model.UserVO;
import com.smhrd.sroup.service.StudyService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class StudyController {

    @Autowired
    private StudyService studyService;

    @GetMapping("/getStudyContent")
    public Map<String, String> getStudyContent(@RequestParam String date, HttpSession session) {
        Map<String, String> response = new HashMap<>();

        // 유저 정보 확인
        UserVO user = (UserVO) session.getAttribute("user");
        if (user == null) {
            response.put("content", "로그인이 필요합니다.");
            return response;
        }

        // 세션에서 스터디 코드 가져오기
        int joinedStudyCd = (int) session.getAttribute("joined_study_cd");
        String content = studyService.getStudyContentByDate(user.getUser_id(), joinedStudyCd, date);

        // JSON 응답 생성
        response.put("content", content != null ? content : "작성된 내용이 없습니다.");
        return response;
    }
}
