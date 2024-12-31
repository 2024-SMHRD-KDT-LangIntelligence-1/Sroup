package com.smhrd.sroup.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@Service
public class RecommendationService {

    private final RestTemplate restTemplate;

    public RecommendationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getRecommendations(String userKeywords) {
        String apiUrl = "http://localhost:5000/recommend"; // Python Flask API URL

        // JSON 요청 본문 작성
        JSONObject requestBody = new JSONObject();
        requestBody.put("keywords", userKeywords);

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // 요청 엔터티 생성
        HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);

        // API 호출
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);

        // 응답 반환
        return response.getBody();
    }
}