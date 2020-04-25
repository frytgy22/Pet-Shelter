package org.lebedeva.pet.model.animal.cat;

import lombok.*;
import org.lebedeva.pet.model.animal.Gender;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cats")
@RequiredArgsConstructor
@ToString(exclude = "breed")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "breed_fk", nullable = false)
    private CatBreed breed;

    @Past
    @NonNull
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    private String photo;

    @NonNull
    @NotBlank
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    private String description;
}
