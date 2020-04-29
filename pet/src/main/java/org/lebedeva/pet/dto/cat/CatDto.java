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
    @NotNull(message = "must be set")
    private Integer breedId;

    private String breedName;

    @PastOrPresent(message = "must be past or present")
    @NonNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    private String photo;

    @NonNull
    @NotNull(message = "must be set")
    @Pattern(regexp = "GIRL|BOY", message = "must be girl or boy")
    private String gender;

    @NonNull
    @NotBlank(message = "must not be blank")
    @Length(max = 5000, message = "length must be < 5000 symbols")
    private String description;
}
