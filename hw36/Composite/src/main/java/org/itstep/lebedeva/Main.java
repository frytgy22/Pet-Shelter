package org.itstep.lebedeva;

import org.itstep.lebedeva.corporation.*;

/*
1. Используя паттерн Composite реализовать структуру корпорации MS, которая содержит подразделения HH, Market, Development.
Подразделение Market включает департаменты: USA, UK, EU. Подразделение Development содержит департаменты: Game, Office, OS.
Каждое подразделение и департамент должны предоставлять отчет о своей деятельности каждый месяц.
Реализовать сбор этих отчетов и отображения всей структуры корпорации

2. Реализовать паттерн итератор для структуры корпорации MS
 */
public class Main {
    public static void main(String[] args) {
        Subdivision corporation = new Subdivision("MS");

        Subdivision development = new Subdivision("Development");
        corporation.add(development);

        Department game = new Department("Game");
        Department office = new Department("Office");
        development.add(game);
        development.add(office);

        Subdivision usa = new Subdivision("USA");
        Department github = new Department("github");
        usa.add(github);
        development.add(usa);

        for (Item item : corporation) {
            System.out.println(item);
        }
    }
}
