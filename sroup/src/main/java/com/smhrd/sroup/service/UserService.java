package com.smhrd.sroup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smhrd.sroup.mapper.UserInterestMapper;
import com.smhrd.sroup.mapper.UserKeywordMapper;
import com.smhrd.sroup.mapper.UserMapper;
import com.smhrd.sroup.model.UserInterestVO;
import com.smhrd.sroup.model.UserKeywordVO;
import com.smhrd.sroup.model.UserVO;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;

	@Autowired
	UserInterestMapper userInterestMapper;
	
	@Autowired
	UserKeywordMapper userKeywordMapper;

	@Transactional
	public void userSignUp(String name, String phone, String email, String password, List<String> interests,
	        List<String> personalities) {
	    try {
	    	
	    	// 유저 정보 세팅 
	        UserVO user = new UserVO();
	        user.setUser_name(name);
	        user.setUser_phone(phone);
	        user.setUser_id(email);
	        user.setUser_pw(password);

	        // 유저 정보 insert
	        userMapper.createAccount(user);

	        // 유저의 관심 분야 세팅 및 insert
	        UserInterestVO uiVO = new UserInterestVO();
	        for (String interest : interests) {
	            uiVO.setUser_id(email);
	            uiVO.setIntr_cd(Integer.parseInt(interest));

	            userInterestMapper.createUserInterest(uiVO);
	        }
	        
	        // 유저의 선호 스턷 분위기 세팅 및 insert
	        UserKeywordVO ukVO = new UserKeywordVO();
	        
	        for(String personality : personalities) {
	        	ukVO.setUser_id(email);
	        	ukVO.setKeyword_cd(Integer.parseInt(personality));
	        	
	            userKeywordMapper.createUserKeyword(ukVO);
	        }
	        
	    } catch (Exception e) {
	        System.err.println("Error during userSignUp: " + e.getMessage());
	        throw e;
	    }
	}

}
