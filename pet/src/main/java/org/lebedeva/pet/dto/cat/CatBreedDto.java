package org.lebedeva.pet.dto.cat;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class CatBreedDto {

    private Integer id;

    @NonNull
    @NotBlank
    @Length(max = 100)
    private String name;

    private Set<CatDto> cats = new HashSet<>();
}
