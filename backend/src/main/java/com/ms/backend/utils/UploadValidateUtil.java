package com.ms.backend.utils;

import com.ms.backend.exceptions.InvalidUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Component
public class UploadValidateUtil {
    private static final long MAX_SIZE = 5 * 1024 * 1024;
    private static final Set<String> ALLOWED_TYPES =
            Set.of("image/jpeg", "image/png");

    public void validate(MultipartFile file) {
        if (file.isEmpty()) {
            throw new InvalidUploadException("File cannot be empty", HttpStatus.BAD_REQUEST);
        }

        if (file.getSize() > MAX_SIZE) {
            throw new InvalidUploadException("File is too large", HttpStatus.CONTENT_TOO_LARGE);
        }

        String contentType = file.getContentType();

        if (contentType == null || !ALLOWED_TYPES.contains(contentType)) {
            throw new InvalidUploadException("File type is not accepted", HttpStatus.BAD_REQUEST);
        }
    }

    public static String getUniqueFileName(String fileName, String extension) {
        return fileName + "_" + System.currentTimeMillis() + extension;
    }

    public static String getFileExtension(MultipartFile file) {
        String filename = file.getOriginalFilename();

        if (filename == null || !filename.contains(".")) {
            return "";
        }

        return filename.substring(filename.lastIndexOf("."));
    }

    public static String getFileName(MultipartFile file) {
        String filename = file.getOriginalFilename();
        return filename.substring(0, filename.lastIndexOf("."));
    }
}
