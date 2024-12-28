package com.smhrd.sroup.entity;

import java.sql.Timestamp;

import com.smhrd.sroup.model.UserVO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_user")		// 테이블 이름 매핑
public class UserEntity {

	public UserEntity(UserVO vo) {
		this.userId = vo.getUserId();
		this.userPw = vo.getUserPw();
		this.userName = vo.getUserName();
		this.userPhone = vo.getUserPhone();
		this.userProfileImg = vo.getUserProfileImg();
		this.joinedAt = vo.getJoinedAt();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false, length = 50) // user_id는 primary key
	private String userId;

	@Column(name = "user_pw", nullable = false, length = 100) // 비밀번호 필드
	private String userPw;

	@Column(name = "user_name", nullable = false, length = 50) // 이름
	private String userName;

	@Column(name = "user_phone", unique = true, length = 20) // 연락처 (unique)
	private String userPhone;

	@Column(name = "user_profile_img", length = 255) // 프로필 이미지 (nullable 가능)
	private String userProfileImg;

	@Column(name = "joined_at", nullable = false) // 가입 일시
	private Timestamp joinedAt;
}
