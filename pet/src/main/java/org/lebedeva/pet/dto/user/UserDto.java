package org.lebedeva.pet.dto.user;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.lebedeva.pet.model.user.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {

    private Integer id;

    @NonNull
    @NotBlank(message = "must be not blank")
    @Length(max = 30)
    private String name;

    @Email(message = "must be email")
    @NonNull
    private String email;

    @NonNull
    @NotBlank(message = "must be not blank")
    private String password;

    @NonNull
    @NotBlank(message = "must be not blank")
    private String confirmPassword;

    @NonNull
    @NotNull(message = "must be set")
    private Set<Role> roles = new HashSet<>();
}
