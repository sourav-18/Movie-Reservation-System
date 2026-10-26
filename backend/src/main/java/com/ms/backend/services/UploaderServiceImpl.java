package com.ms.backend.services;

import com.cloudinary.Cloudinary;
import com.ms.backend.utils.UploadValidateUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class UploaderServiceImpl {

    private final File uploadDirectory;
    private final Cloudinary cloudinary;

    public UploaderServiceImpl(
            @Value("${app.upload.directory-path}") String directoryPath, Cloudinary cloudinary) {
        this.uploadDirectory = new File(directoryPath).getAbsoluteFile();
        this.cloudinary = cloudinary;
    }

    public String storeInLocalStorage(MultipartFile file) {
        try {
            prepareDirectory();
            String storedName = UploadValidateUtil.getUniqueFileName(
                    UploadValidateUtil.getFileName(file),
                    UploadValidateUtil.getFileExtension(file));
            File destination =
                    new File(uploadDirectory, storedName);

            file.transferTo(destination);

            return storedName;
        } catch (IOException ex) {
            throw new RuntimeException(
                    "Could not store uploaded file", ex
            );
        }
    }

//    public String uploadInCloudnary(MultipartFile file) throws IOException {
//        String storedName = UploadValidateUtil.getUniqueFileName(
//                UploadValidateUtil.getFileName(file),
//                UploadValidateUtil.getFileExtension(file));
//        HashMap<Object, Object> options = new HashMap<>();
//        options.put("folder", storedName);
//        Map uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
//        String publicId = (String) uploadedFile.get("public_id");
//        return cloudinary.url().secure(true).generate(publicId);
//    }

    public String uploadInCloudinary(MultipartFile file) throws IOException {
        String storedName = UploadValidateUtil.getUniqueFileName(
                UploadValidateUtil.getFileName(file),
                UploadValidateUtil.getFileExtension(file)
        );

        Map<String, Object> options = new HashMap<>();
        options.put("folder", "mrs/movie/thumbnail");
        options.put("public_id", storedName);

        Map<?, ?> uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
        return (String) uploadedFile.get("secure_url");
    }

    private void prepareDirectory() throws IOException {
        if (uploadDirectory.exists()) {
            if (!uploadDirectory.isDirectory()) {
                throw new IOException(
                        "Upload location is not a directory"
                );
            }

            return;
        }

        if (!uploadDirectory.mkdirs()) {
            throw new IOException(
                    "Could not create upload directory"
            );
        }
    }
}
