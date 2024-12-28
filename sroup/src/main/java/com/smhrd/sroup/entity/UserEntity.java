package com.smhrd.sroup.entity;

import java.sql.Timestamp;

import com.smhrd.sroup.model.UserVO;

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
	
	public UserEntity(UserVO vo) {
		this.user_id=vo.getUser_id();
		this.user_pw=vo.getUser_pw();
		this.user_phone=vo.getUser_phone();
		this.user_profile_img=vo.getUser_profile_img();
		this.joined_at=vo.getJoined_at();
	}

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
