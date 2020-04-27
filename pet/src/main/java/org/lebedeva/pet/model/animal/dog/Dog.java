package org.lebedeva.pet.model.animal.dog;

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
@Table(name = "dogs")
@RequiredArgsConstructor
@ToString(exclude = "breed")
@EqualsAndHashCode(exclude = "breed")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "breed_fk", nullable = false)
    private DogBreed breed;

    @PastOrPresent
    @NonNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    private String photo;

    @NonNull
    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NonNull
    @NotBlank
    @Length(max = 255)
    private String description;
}
