package org.itstep.lebedeva.corporation;

import java.util.ArrayList;
import java.util.List;

public class Corporation {
    private String name;
    private List<Subdivision> corporation;

    public Corporation(String name) {
        this.name = name;
        corporation = new ArrayList<>();
    }

    public void add(Subdivision corp) {
        corporation.add(corp);
    }

    private void remove(Subdivision corp) {
        corporation.remove(corp);
    }

    public List<Subdivision> getCorporation() {
        return corporation;
    }

    @Override
    public String toString() {
        return "Corporation{" +
                "name='" + name + '\'' +
                '}';
    }
}
