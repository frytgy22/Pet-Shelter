package org.lebedeva.pet.model.post;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
@RequiredArgsConstructor
@ToString(exclude = "categories")
@EqualsAndHashCode(exclude = "categories")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NonNull
    @NotBlank
    @Length(max = 255)
    private String title;

    @NotNull
    @NonNull
    @NotBlank
    @Length(max = 1000)
    private String subtitle;

    @NotNull
    @NonNull
    @NotBlank
    @Length(max = 65535)
    @Column(length = 65535, columnDefinition = "Text")
    private String contents;

    @NonNull
    @PastOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    private String file;

    @ManyToMany(fetch = FetchType.EAGER)//TODO
    @JoinTable(name = "post_categories",
            joinColumns = @JoinColumn(name = "posts_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id"))
    private Set<Category> categories = new HashSet<>();
}