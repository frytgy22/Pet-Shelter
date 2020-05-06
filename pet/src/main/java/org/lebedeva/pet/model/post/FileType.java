package org.lebedeva.pet.model.post;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;


public interface FileType {

    static String getFileType(MultipartFile multipartFile) {

        List<String> imagesContentTypes = Arrays.asList("image/png", "image/jpg", "image/jpeg");
        List<String> videoContentTypes = Arrays.asList("video/ogg", "video/webm", "video/mp4");

        if (multipartFile != null && !multipartFile.isEmpty() && multipartFile.getSize() > 0) {

            if (imagesContentTypes.stream()
                    .anyMatch(s -> s.equals(multipartFile.getContentType()))) {
                return "image";
            } else if (videoContentTypes.stream()
                    .anyMatch(s -> s.equals(multipartFile.getContentType()))) {
                return "video";
            }
        }
        return "";
    }
}
