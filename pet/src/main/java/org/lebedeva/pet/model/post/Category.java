package org.lebedeva.pet.model.post;

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
@Table(name = "categories")
@ToString(exclude = "posts")
@EqualsAndHashCode(exclude = "posts")
public class Category {
//    HEALTH, MEDICINE, NUTRITION, GAMES,
//    TRAINING, CARE, CATS, DOGS, FUN,
//    ADVICE, INFO;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @NotBlank
    @Length(max = 50)
    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinTable(name = "post_categories",
            joinColumns = @JoinColumn(name = "categories_id"),
            inverseJoinColumns = @JoinColumn(name = "posts_id"))
    private Set<Post> posts = new HashSet<>();
}
