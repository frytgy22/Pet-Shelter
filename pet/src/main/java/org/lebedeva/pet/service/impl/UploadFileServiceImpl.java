package org.lebedeva.pet.service.impl;

import org.lebedeva.pet.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    private final ResourceLoader resourceLoader;

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Autowired
    public UploadFileServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public List<String> listFiles(String uploadsDir) throws IOException {
        Resource resource = resourceLoader.getResource(uploadsDir);
        if (resource.exists()) {
            return Files.list(Paths.get(resource.getURI()))
                    .map(path -> path.getFileName().toString())
                    .collect(Collectors.toList());
        }
        return null;
    }

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
