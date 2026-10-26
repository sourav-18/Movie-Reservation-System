package com.ms.backend.services;

import com.cloudinary.Cloudinary;
import com.ms.backend.utils.UploadValidateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Primary
@Service
@RequiredArgsConstructor
public class CloudinaryUploadServiceImpl implements UploaderService{

    private final Cloudinary cloudinary;
    @Override
    public String upload(MultipartFile file, String folderName) {
        String storedName = UploadValidateUtil.getUniqueFileName(
                UploadValidateUtil.getFileName(file),
                UploadValidateUtil.getFileExtension(file)
        );

        Map<String, Object> options = new HashMap<>();
        options.put("folder", "mrs/"+folderName);
        options.put("public_id", storedName);

        Map<?, ?> uploadedFile = null;
        try {
            uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return (String) uploadedFile.get("secure_url");
    }
}
