package com.smhrd.sroup.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/recommend")
public class RecommendationControllerReview {

    @PostMapping
    public ResponseEntity<?> getRecommendations(@RequestBody Map<String, String> request) {
        String userId = request.get("user_id");
        if (userId == null || userId.isEmpty()) {
            return ResponseEntity.badRequest().body("user_id is required");
        }

        // Flask API URL
        String flaskApiUrl = "http://127.0.0.1:5000/recommend";

        // RestTemplate으로 Python Flask API 호출
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(flaskApiUrl, HttpMethod.POST, entity, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to connect to Python API");
        }
    }
}