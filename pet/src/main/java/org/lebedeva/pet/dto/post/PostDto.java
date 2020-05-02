package org.lebedeva.pet.dto.post;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class PostDto {

    private Integer id;

    @NonNull
    @NotBlank
    @Length(max = 255, message = "length must be < 255 symbols")
    private String title;

    @NonNull
    @NotBlank
    @Length(max = 1000, message = "length must be < 1000 symbols")
    private String subtitle;

    @NonNull
    @NotBlank
    @Length(max = 65535, message = "length must be < 165535 symbols")
    private String contents;

    @PastOrPresent(message = "must be past or present")
    @NonNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    private String file;

    @NonNull
    @NotNull(message = "must be set")
    private Set<Integer> categoryId = new HashSet<>();

    private Set<String> categoryName = new HashSet<>();
}
