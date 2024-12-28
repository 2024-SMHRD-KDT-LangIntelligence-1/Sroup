package com.smhrd.sroup.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

	// NN
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, length = 50)	// not null = true
	private String user_id;
	
	@Column(nullable = false, length = 50)	// not null = true
	private String user_pw;
	
	@Column(nullable = false, length = 50)	// not null = true
	private String user_name;
	
	@Column(unique = true, length = 20)
	private String user_phone;
	
	@Column(nullable = false, length = 1000)	// not null = true
	private String user_profile_img;
	
	@Column(nullable = false)	// not null = true
	private Timestamp joined_at;
}
