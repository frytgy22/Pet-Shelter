package org.lebedeva.pet.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFileService {

    void uploadFile(MultipartFile file, String dir, Integer folderName) throws IOException;
}
