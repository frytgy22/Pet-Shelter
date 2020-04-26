package org.lebedeva.pet.dto.cat;

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
public class CatDto {

    private Integer id;

    @NonNull
    @NotNull
    private Integer breedId;

    private String breedName;

    @PastOrPresent
    @NonNull
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    private String photo;

    @NonNull
    @NotNull
    @Pattern(regexp = "GIRL|BOY")
    private String gender;

    @NonNull
    @NotBlank
    @Length(max = 255)
    private String description;
}
