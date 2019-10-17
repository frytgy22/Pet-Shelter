package org.itstep.lebedeva;

import org.itstep.lebedeva.corporation.Corporation;
import org.itstep.lebedeva.iterator.Container;
import org.itstep.lebedeva.iterator.Iterator;

import java.util.List;

public class CorporationRepository implements Container {
    private List list;

    public CorporationRepository(Corporation corporation) {
        list = corporation.getCorporation();
    }

    @Override
    public Iterator getIterator() {
        return new NameIterator();
    }

    private class NameIterator implements Iterator {
        int index;

        @Override
        public boolean hasNext() {
            return index < list.size();
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return list.get(index++);
            }
            return null;
        }
    }
}
