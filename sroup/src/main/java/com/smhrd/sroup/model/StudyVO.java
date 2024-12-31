package com.smhrd.sroup.model;

import java.sql.Timestamp;

public class StudyVO {
    private int study_cd;          // 스터디 고유 코드
    private String study_title;    // 스터디 제목
    private String study_content;  // 스터디 내용
    private Timestamp closed_at;   // 모집 마감 날짜
    private int study_limit;       // 모집 인원
    private int current_members;   // 현재 멤버 수

    // Getters and Setters
    public int getStudy_cd() {
        return study_cd;
    }

    public void setStudy_cd(int study_cd) {
        this.study_cd = study_cd;
    }

    public String getStudy_title() {
        return study_title;
    }

    public void setStudy_title(String study_title) {
        this.study_title = study_title;
    }

    public String getStudy_content() {
        return study_content;
    }

    public void setStudy_content(String study_content) {
        this.study_content = study_content;
    }

    public Timestamp getClosed_at() {
        return closed_at;
    }

    public void setClosed_at(Timestamp closed_at) {
        this.closed_at = closed_at;
    }

    public int getStudy_limit() {
        return study_limit;
    }

    public void setStudy_limit(int study_limit) {
        this.study_limit = study_limit;
    }

    public int getCurrent_members() {
        return current_members;
    }

    public void setCurrent_members(int current_members) {
        this.current_members = current_members;
    }
}

