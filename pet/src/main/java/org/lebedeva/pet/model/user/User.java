package org.lebedeva.pet.model.user;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @NotBlank
    @Length(max = 30)
    private String name;

    @Email
    @NonNull
    @Column(unique = true, nullable = false)
    private String email;

    @NonNull
    @NotBlank
    private String password;

    @NonNull
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;
}
