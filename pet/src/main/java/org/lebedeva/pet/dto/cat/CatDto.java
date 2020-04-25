package org.lebedeva.pet.dto.cat;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class CatDto {

    private Integer id;

    @NotNull
    @NonNull
    private Integer breedId;

    private String breedName;

    @Past
    @NonNull
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    private String photo;

    @NonNull
    @NotBlank
    @Digits(integer = 1, fraction = 0)
    //@Pattern(regexp = "[0-1]")
    private Integer gender;

    private String description;
}
