package org.itstep;

import java.io.File;

public class SingletonFile {
    private static SingletonFile singleInstance = null;
    public File file = new File(".");

    private SingletonFile() {
    }

    public static SingletonFile getInstance() {
        if (singleInstance == null) {
            singleInstance = new SingletonFile();
        }
        return singleInstance;
    }
}
