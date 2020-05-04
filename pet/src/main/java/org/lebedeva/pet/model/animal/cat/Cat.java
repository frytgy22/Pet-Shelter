package org.lebedeva.pet.model.animal.cat;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.lebedeva.pet.model.animal.Gender;
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
@Table(name = "cats")
@RequiredArgsConstructor
@ToString(exclude = "breed")
@EqualsAndHashCode(exclude = "breed")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "breed_fk", nullable = false)
    private CatBreed breed;

    @NonNull
    @PastOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    private String photo;

    @NonNull
    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NonNull
    @NotNull
    @NotBlank
    @Length(max = 5000)
    private String description;
}
