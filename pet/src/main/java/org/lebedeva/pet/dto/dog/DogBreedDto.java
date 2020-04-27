package org.lebedeva.pet.dto.dog;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class DogBreedDto {

    private Integer id;

    @NonNull
    @NotBlank
    @Length(max = 100)
    private String name;

    private Set<DogDto> dogs = new HashSet<>();
}
