package org.itstep.lebedeva.corporation;

import java.util.ArrayList;
import java.util.List;

public class Subdivision {
    private String name;
    private List<Department> department;

    public Subdivision(String name) {
        this.name = name;
        department = new ArrayList<>();
    }

    public void add(Department d) {
        department.add(d);
    }

    private void remove(Department d) {
        department.remove(d);
    }

    public List<Department> getDepartment() {
        return department;
    }

    public String getReport() {
        return "Subdivision{" +
                "name='" + name +
                '}' + " sent report!";
    }

    @Override
    public String toString() {
        return "Subdivision{" +
                "name='" + name + '\'' +
                ", department=" + department +
                '}';
    }
}
