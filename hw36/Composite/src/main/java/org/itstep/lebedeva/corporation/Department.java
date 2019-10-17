package org.itstep.lebedeva.corporation;


public class Department {
    private String name;

    public Department(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getReport() {
        return "Department{" +
                "name='" + name + '\'' +
                '}' + " sent report!";
    }
}
