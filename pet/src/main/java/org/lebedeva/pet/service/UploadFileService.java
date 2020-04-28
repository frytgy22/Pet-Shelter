package org.lebedeva.pet.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UploadFileService {
    List<String> listFiles(String dir) throws IOException;

    void uploadFile(MultipartFile file, String dir, Integer folderName) throws IOException;
}
