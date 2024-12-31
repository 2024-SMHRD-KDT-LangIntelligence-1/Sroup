package com.smhrd.sroup.controller;

import com.smhrd.sroup.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping("/recommend")
    public String recommend(@RequestBody String userKeywords) {
        return recommendationService.getRecommendations(userKeywords);
    }
} 