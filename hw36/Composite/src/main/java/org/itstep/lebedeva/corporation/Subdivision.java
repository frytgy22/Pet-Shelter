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
        return "Подразделение: " + name + '\n';
    }

    private static class SubdivisionIterator implements Iterator<Item> {

        Iterator<Item> iterator;

        public SubdivisionIterator(Subdivision sub) {
            List<Item> all = new ArrayList<>();
            all.add(sub);
            all.addAll(makeFlatList(sub.department.iterator()));
            iterator = all.iterator();
        }

        List<Item> makeFlatList(Iterator<Item> it) {
            List<Item> list = new ArrayList<>();
            while (it.hasNext()) {
                Item item = it.next();
                list.add(item);
                if (item instanceof Subdivision) {
                    Subdivision subdiv = (Subdivision) item;
                    list.addAll(makeFlatList(subdiv.department.iterator()));
                }
            }
            return list;
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Item next() {
            return iterator.next();
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new SubdivisionIterator(this);
    }
}
