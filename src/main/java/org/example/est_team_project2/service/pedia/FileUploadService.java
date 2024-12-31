package org.example.est_team_project2.service.pedia;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadService {

    // 실제 파일이 저장될 디렉토리 경로 (src/main/resources/static/uploads)
    private final Path uploadDir = Paths.get("src/main/resources/static/uploads");

    public String storeFile(MultipartFile file) {
        try {
            // 업로드 디렉토리가 없으면 생성
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            // 파일 이름 생성 (현재 시간 + 원래 파일 이름)
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = uploadDir.resolve(fileName);

            // 파일 저장
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // 파일 경로 반환 (웹에서 접근 가능한 경로, 예: /uploads/filename)
            return "/uploads/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패", e);
        }
    }
}

