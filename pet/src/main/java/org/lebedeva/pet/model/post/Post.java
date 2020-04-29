package org.lebedeva.pet.model.post;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
@RequiredArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @NotBlank
    @Length(max = 255)
    private String title;

    @NonNull
    @NotBlank
    @Length(max = 1000)
    private String subtitle;

    @NonNull
    @NotBlank
    @Length(max = 65535)
    @Column(length = 65535, columnDefinition = "Text")
    private String contents;

    @PastOrPresent
    @NonNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    @NonNull
    @NotNull
    @Enumerated(EnumType.STRING)
    private Category category;

    private String photo;
}
