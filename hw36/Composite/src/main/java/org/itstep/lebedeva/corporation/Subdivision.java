package org.itstep.lebedeva.corporation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Subdivision implements Item {
    private String name;
    private List<Item> department;

    public Subdivision(String name) {
        this.name = name;
        department = new ArrayList<>();
    }

    public void add(Item d) {
        department.add(d);
    }

    public String getReport() {
        StringBuilder report = new StringBuilder("Subdivision{" + "name='" + name + '\'' + ",sent report!\n");
        for (Item item : department) {
            report.append(item.getReport());
        }
        return report.toString();
    }

    @Override
    public String toString() {
        return "Subdivision{" + "name='" + name + '\'' + ", department=" + department + '}';
    }

    @Override
    public Iterator<Item> iterator() {
        return department.iterator();
    }
}
