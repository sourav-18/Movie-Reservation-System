package com.ms.backend.services;

import org.springframework.web.multipart.MultipartFile;

public interface UploaderService {
    String upload(MultipartFile file,String folderName);
}
