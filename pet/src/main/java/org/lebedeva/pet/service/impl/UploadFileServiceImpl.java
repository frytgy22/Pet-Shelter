package org.lebedeva.pet.service.impl;

import org.lebedeva.pet.service.UploadFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Override
    public void uploadFile(MultipartFile file, String dir, Integer folderName) {

        try {
            Path copyLocation = Paths.get(uploadDir + dir + folderName);

            Path directories = Files.createDirectories(copyLocation);
            Path uploadDir = Paths.get(directories + File.separator
                    + StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));

            Files.copy(file.getInputStream(), uploadDir, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
