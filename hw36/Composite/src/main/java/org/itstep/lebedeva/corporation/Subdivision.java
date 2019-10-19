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

    private void remove(Item d) {
        department.remove(d);
    }

    public List<Item> getDepartment() {
        return department;
    }

    public String getReport() {
        String report = "Reprot " + name + "\n";
        for (Item item : department) {
            report += department.getReport();
        }

        return report;
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
