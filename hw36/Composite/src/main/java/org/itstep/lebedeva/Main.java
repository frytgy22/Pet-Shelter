package org.itstep.lebedeva;

import org.itstep.lebedeva.corporation.Corporation;
import org.itstep.lebedeva.corporation.Department;
import org.itstep.lebedeva.corporation.Subdivision;
import org.itstep.lebedeva.iterator.Iterator;

/*
1. Используя паттерн Composite реализовать структуру корпорации MS, которая содержит подразделения HH, Market, Development.
Подразделение Market включает департаменты: USA, UK, EU. Подразделение Development содержит департаменты: Game, Office, OS.
Каждое подразделение и департамент должны предоставлять отчет о своей деятельности каждый месяц.
Реализовать сбор этих отчетов и отображения всей структуры корпорации

2. Реализовать паттерн итератор для структуры корпорации MS
 */
public class Main {
    public static void main(String[] args) {
        Corporation corporation = new Corporation("MS");

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

        market.add(usa);
        market.add(uk);
        market.add(eu);

        development.add(game);
        development.add(office);
        development.add(os);

        getStructureCorporation(corporation);
        System.out.println();
        getReportsCorporation(corporation);
    }

    public static void getStructureCorporation(Corporation corporation) {
        System.out.println(corporation);
        CorporationRepository corporationRepository = new CorporationRepository(corporation);
        for (Iterator iterator = corporationRepository.getIterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }
    }

    public static void getReportsCorporation(Corporation corporation) {
        CorporationRepository corporationRepository = new CorporationRepository(corporation);

        for (Iterator iterator = corporationRepository.getIterator(); iterator.hasNext(); ) {
            Subdivision subdivision = (Subdivision) iterator.next();
            System.out.println(subdivision.getReport());

            for (Department department : subdivision.getDepartment()) {
                System.out.println(department.getReport());
            }
        }
    }
}

