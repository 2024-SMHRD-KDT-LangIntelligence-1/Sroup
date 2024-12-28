package com.smhrd.sroup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.sroup.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, String>{

	
	UserEntity findBy
}
