package org.itstep.lebedeva.corporation;

import java.util.Iterator;

public class Department implements Item {
    private String name;

    public Department(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" + "name='" + name + '\'' + '}';
    }

    public String getReport() {
        return "Department{" + "name='" + name + '\'' + '}' + ",sent report!\n";
    }

    static class DepartmentIterator implements Iterator<Item> {

        Department department;

        DepartmentIterator(Department d) {
            department = d;
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Item next() {
            return department;
        }

    }

    @Override
    public Iterator<Item> iterator() {
        return new DepartmentIterator(this);
    }
}
