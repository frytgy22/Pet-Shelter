package org.lebedeva.pet.model.animal.dog;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "dog_breed")
@ToString(exclude = "dogs")
@EqualsAndHashCode(exclude = "dogs")
public class DogBreed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NonNull
    @Length(max = 50)
    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "breed",
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    private Set<Dog> dogs = new HashSet<>();
}
