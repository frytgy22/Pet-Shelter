package org.itstep;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 10L;
    private String login;
    private String password;
    private String gender;
    private String phone;
    private String email;
    private String subscribe;
}
