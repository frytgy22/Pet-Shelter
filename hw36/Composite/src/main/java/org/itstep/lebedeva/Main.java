package org.itstep.lebedeva;

import java.util.Iterator;

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

        Subdivision hh = new Subdivision("HH");
        Subdivision market = new Subdivision("Market");
        Subdivision development = new Subdivision("Development");

        Department usa = new Department("USA");
        Department uk = new Department("UK");
        Department eu = new Department("EU");

        Department game = new Department("Game");
        Department office = new Department("Office");
        Department os = new Department("OS");

        corporation.add(hh);
        corporation.add(market);
        corporation.add(development);

        hh.add(new Subdivision("Demo"));

        market.add(usa);
        market.add(uk);
        market.add(eu);

        development.add(game);
        development.add(office);
        development.add(os);

        corporation.getReport();

        for (Item item : corporation) {
            item.getReport();
        }
    }
}
