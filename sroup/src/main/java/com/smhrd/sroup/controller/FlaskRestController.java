package com.smhrd.sroup.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8082")
public class FlaskRestController {
	private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/send-to-flask")
    public ResponseEntity<Map<String,Object>> sendToFlask(@RequestBody Map<String, Object> requestData) {
        // Flask 서버 URL
        String flaskUrl = "http://127.0.0.1:8083/process-spring";

        // Flask로 POST 요청
        ResponseEntity<Map> response = restTemplate.postForEntity(flaskUrl, requestData, Map.class );
        // Flask 응답 반환
        return ResponseEntity.ok(response.getBody());
    }
    
    @PostMapping("/saveSession")
    public String saveSession(@RequestBody Map<String, List<String>> data, HttpSession session) {
    	System.out.println("13123123123123:"+data.get("title"));

    	for(int i = 0; i<20; i++) {
    		
    		session.setAttribute("data"+i, data.get("title").get(i)); // 첫 번째 제목
    	}
        return "result";
    }
    
}
