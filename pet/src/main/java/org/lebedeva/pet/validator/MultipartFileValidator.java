package org.lebedeva.pet.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;


@Component
public class MultipartFileValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == MultipartFile.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        MultipartFile fileUploadModel = (MultipartFile) o;

        if (!fileUploadModel.isEmpty()) {

            if (fileUploadModel.getSize() > 5_000_000) {
                errors.rejectValue("photo", "file.size");
            }

            List<String> validContentTypes = Arrays.asList("image/png", "image/jpg", "image/jpeg");

            if (validContentTypes.stream()
                    .noneMatch(s -> s.equals(fileUploadModel.getContentType()))) {
                errors.rejectValue("photo", "file.type");
            }
        }
    }
}