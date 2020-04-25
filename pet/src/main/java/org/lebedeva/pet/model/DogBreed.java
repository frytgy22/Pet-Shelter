package org.lebedeva.pet.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "dog_breed")
@ToString(exclude = {"dogs"})
public class DogBreed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @NotBlank
    @Length(max = 100)
    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "breed",
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    private Set<Dog> dogs = new HashSet<>();
}
