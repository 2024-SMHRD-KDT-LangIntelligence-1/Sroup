package com.smhrd.sroup.service;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.sroup.entity.FileEntity;
import com.smhrd.sroup.repository.FileRepository;

@Service

public class FileService {
	
	 @Value("${file.upload-dir}")
	    private String uploadDir;
	 
	     private final FileRepository fileRepository;
	
	     public FileService(FileRepository fileRepository) {
	         this.fileRepository = fileRepository;
	     }
	     
	    public FileEntity saveFile(MultipartFile file, String uploadedBy) throws IOException {
	    	// 파일 이름 및 경로 설정
	    	String fileName = file.getOriginalFilename();
	    	 if (fileName == null || fileName.isEmpty()) {
	    	        throw new IOException("Invalid file name");
	    	    }
	        String fileType = file.getContentType();
	        long fileSize = file.getSize();

	        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	        String filePath = uploadDir + "/" + timestamp + "_" + fileName;
	        
	        // 업로드 디렉터리가 없는 경우 생성
	        File directory = new File(uploadDir);
	        if (!directory.exists() && !directory.mkdirs()) {
	            throw new IOException("Could not create upload directory: " + uploadDir);
	        }
	        // 파일 저장
	        Files.copy(file.getInputStream(), Paths.get(filePath));
	        
	        // 파일 정보 데이터베이스 저장
	        FileEntity fileEntity = new FileEntity();
	        fileEntity.setFileName(fileName);
	        fileEntity.setFileType(file.getContentType());
	        fileEntity.setFilePath(filePath);
	        fileEntity.setFileSize(file.getSize());
	        fileEntity.setUploadedBy(uploadedBy);
	        fileEntity.setUploadDate(LocalDateTime.now().toString());

	        return fileRepository.save(fileEntity);
	    }

	    public FileEntity getFileById(Long id) {
	        return fileRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("파일이 존재하지 않습니다."));
	    }

	    public byte[] getFileContent(String filePath) throws IOException {
	        return Files.readAllBytes(Paths.get(filePath));
	    }

	    public Iterable<FileEntity> getAllFiles() {
	        return fileRepository.findAll();
	    }
	    
//	     삭제 메서드 
	    public void deleteFileById(Long id) throws IOException {
	        FileEntity fileEntity = fileRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("File not found with id: " + id));

	        File file = new File(fileEntity.getFilePath());
	        if (file.exists() && !file.delete()) {
	            throw new IOException("Failed to delete file from system: " + fileEntity.getFilePath());
	        }

	        fileRepository.deleteById(id);

	
	    }
}
