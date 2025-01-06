package com.smhrd.sroup.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.smhrd.sroup.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

}
