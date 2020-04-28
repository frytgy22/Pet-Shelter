package org.lebedeva.pet.dto.dog;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class DogDto {

    private Integer id;

    @NonNull
    @NotNull
    private Integer breedId;

    private String breedName;

    @PastOrPresent
    @NonNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    private String photo;

    @NonNull
    @NotNull(message = "must be set")
    @Pattern(regexp = "GIRL|BOY")
    private String gender;

    @NonNull
    @NotBlank
    @Length(max = 5000)
    private String description;
}
