package org.itstep;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SingletonListJobs {
    private static SingletonListJobs singleInstance = null;
    public List<String> listJobs = Collections.synchronizedList(new ArrayList<>());

    private SingletonListJobs() {
    }

    public static SingletonListJobs getInstance() {
        if (singleInstance == null) {
            singleInstance = new SingletonListJobs();
        }
        return singleInstance;
    }
}
