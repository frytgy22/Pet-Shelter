package org.itstep;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NonNull
public class User {
    private String email;
    private String login;
    private String password;
    private String phone;
    private String dateOfBirth;
    private int gender;

}
