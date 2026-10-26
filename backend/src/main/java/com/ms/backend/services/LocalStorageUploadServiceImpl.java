package com.ms.backend.services;

import com.ms.backend.utils.UploadValidateUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Service
public class LocalStorageUploadServiceImpl implements UploaderService {

    @Value("${app.upload.base-directory-path}")
    private String baseDirectoryPath;

    @Override
    public String upload(MultipartFile file, String folderName) {
        try {
            File uploadDirectory = new File(baseDirectoryPath + "/" + folderName).getAbsoluteFile();
            prepareDirectory(uploadDirectory);
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

    private void prepareDirectory(File uploadDirectory) throws IOException {
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
