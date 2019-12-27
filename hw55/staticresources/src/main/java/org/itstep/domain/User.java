package org.itstep.domain;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
public class User {
    private int id;
    private String login;
    private String password;
    private int coins;
    private String date;

    public User(BuilderUser builderUser) {
        this.id = builderUser.getId();
        this.login = builderUser.getLogin();
        this.password = builderUser.getPassword();
        this.coins = builderUser.getCoins();
        this.date = builderUser.getDate();
    }
}

