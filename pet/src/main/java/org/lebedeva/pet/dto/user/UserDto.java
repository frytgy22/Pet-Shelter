package org.lebedeva.pet.dto.user;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {

    private Integer id;

    @NonNull
    @NotBlank
    @Length(max = 30)
    private String name;

    @Email
    @NonNull
    private String email;

    @NonNull
    @NotBlank
    private String password;

    @NonNull
    @NotBlank
    private String confirmPassword;

    @NonNull
    @NotNull
    private String roles;
}
