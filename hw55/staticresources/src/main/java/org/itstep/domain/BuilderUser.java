package org.itstep.domain;

import lombok.Getter;

@Getter
public class BuilderUser {  //создание User с разными параметрами конструктора
    private int id;
    private String login;
    private String password;
    private int coins;
    private String date;

    public BuilderUser() {
    }

    public BuilderUser setID(int id) {
        this.id = id;
        return this;
    }

    public BuilderUser setLogin(String login) {
        this.login = login;
        return this;
    }

    public BuilderUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public BuilderUser setCoins(int coins) {
        this.coins = coins;
        return this;
    }

    public BuilderUser setDate(String date) {
        this.date = date;
        return this;
    }

    public User build() {
        return new User(this);
    }
}
