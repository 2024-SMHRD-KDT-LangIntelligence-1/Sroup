package com.smhrd.sroup.model;

import java.sql.Timestamp;

public class StudyVO {
    private int studyCd;           // 스터디 고유 코드
    private String studyTitle;     // 스터디 제목
    private String studyContent;   // 스터디 내용
    private String userId;         // 작성자 아이디
    private int studyLimit;        // 모집 인원
    private Timestamp closedAt;    // 모집 마감 날짜
    private String studyStatus;    // 모집 상태
    private Timestamp createdAt;   // 등록 일자
    private int currentMembers; // 현재 가입된 멤버 수

    public int getCurrentMembers() {
        return currentMembers;
    }

    public void setCurrentMembers(int currentMembers) {
        this.currentMembers = currentMembers;
    }
    
    public int getStudyCd() {
        return studyCd;
    }

    public void setStudyCd(int studyCd) {
        this.studyCd = studyCd;
    }

    public String getStudyTitle() {
        return studyTitle;
    }

    public void setStudyTitle(String studyTitle) {
        this.studyTitle = studyTitle;
    }

    public String getStudyContent() {
        return studyContent;
    }

    public void setStudyContent(String studyContent) {
        this.studyContent = studyContent;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStudyLimit() {
        return studyLimit;
    }

    public void setStudyLimit(int studyLimit) {
        this.studyLimit = studyLimit;
    }

    public Timestamp getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Timestamp closedAt) {
        this.closedAt = closedAt;
    }

    public String getStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(String studyStatus) {
        this.studyStatus = studyStatus;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}

