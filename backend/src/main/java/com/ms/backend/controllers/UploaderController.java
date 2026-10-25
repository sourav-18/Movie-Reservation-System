package com.ms.backend.controllers;

import com.ms.backend.dtos.response.ApiResponseDto;
import com.ms.backend.services.UploaderService;
import com.ms.backend.utils.UploadValidateUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/uploads")
@RequiredArgsConstructor
public class UploaderController {

    private final UploadValidateUtil uploadValidateUtil;
    private final UploaderService uploaderService;

    @PostMapping("/movie-thumbnail")
    public ResponseEntity<ApiResponseDto<String>> movieThumbnailUpload(@RequestParam("file") MultipartFile file) throws IOException {
        uploadValidateUtil.validate(file);
        String path = uploaderService.uploadInCloudinary(file);
        return ResponseEntity.ok(new ApiResponseDto<>(200, "file upload successfully", path));
    }
}
