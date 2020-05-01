package org.lebedeva.pet.dto.post;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryDto {

    private Integer id;

    @NonNull
    @NotBlank(message = "must not be blank")
    @Length(max = 50, message = "length must be < 255 symbols")
    private String name;

    private Set<PostDto> posts = new HashSet<>();
}
