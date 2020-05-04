package org.lebedeva.pet.validator;

import org.lebedeva.pet.dto.user.UserDto;
import org.lebedeva.pet.model.user.User;
import org.lebedeva.pet.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == UserDto.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDto dto = (UserDto) o;

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "password");
        }

        if (userService.findByEmail(dto.getEmail()).isPresent()) {
            errors.rejectValue("email", "email");
        }
    }
}
