package com.smhrd.sroup.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.sroup.entity.FileEntity;
import com.smhrd.sroup.service.FileService;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/files")

public class FileController {
	
	private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public String listFiles(Model model) {
        model.addAttribute("files", fileService.getAllFiles());
        return "mystudy";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("uploadedBy") String uploadedBy,
                             Model model) {
        try {
        	// 파일 저장 처리
            fileService.saveFile(file, uploadedBy);
            model.addAttribute("message", "File uploaded successfully!");
            return "redirect:/files"; // 업로드 후 파일 목록 페이지로 이동
        } catch (IOException e) {
        	// 예외 로그 출력 및 에러 페이지로 리디렉션
        	System.err.println("File upload error: " + e.getMessage());
            model.addAttribute("message", "Failed to upload file: " + e.getMessage());
            return "error"; // 에러 페이지를 표시 (error.html 생성 필요)
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
    	
        try {
        	// 데이터베이스에서 파일 정보 조회
        	FileEntity fileEntity = fileService.getFileById(id);
        	
        	if (fileEntity == null) {
        		System.out.println("데이터베이스 안에 파일을 찾을수 없습니다.");
                return ResponseEntity.status(404).body(null); // 파일 정보가 없을 경우
            }

            String filePath = fileEntity.getFilePath();
            System.out.println("Downloading file from path: " + filePath);
            
            File file = new File(fileEntity.getFilePath());
            
            // 파일 존재 여부 확인
            if (!file.exists()) {
            	System.out.println("File not found at path: " + filePath);
                return ResponseEntity.status(404).body(null);
            }
     
         // 파일 데이터를 읽어서 Resource로 변환
            ByteArrayResource resource = new ByteArrayResource(fileService.getFileContent(fileEntity.getFilePath()));
            System.out.println("File successfully read, preparing to download...");
            
            // 파일 다운로드 응답 설정
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFileName() + "\"")
                    .contentLength(file.length())
                    .body(resource);
        } catch (IOException e) {
        	 // 에러 로그 출력 및 500 에러 반환
        	System.err.println("Error during file download: " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }


    @PostMapping("/delete/{id}")
    public String deleteFile(@PathVariable Long id, Model model) {
        try {
            System.out.println("Attempting to delete file with ID: " + id);
            fileService.deleteFileById(id);
            model.addAttribute("message", "File deleted successfully!");
        } catch (Exception e) {
            System.err.println("Error deleting file: " + e.getMessage());
            model.addAttribute("message", "Error deleting file: " + e.getMessage());
        }
        return "redirect:/files";
    }

}
