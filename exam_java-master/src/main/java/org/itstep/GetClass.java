package org.itstep;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public interface GetClass {
    static File getsConfigurationClass(Configurations configurations, String line, File file, String forConstructor, int numberConstructor) {
        Class<?> cls = null;
        try {
            cls = Class.forName(configurations.getProperties().getProperty(line));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Constructor<?> constructor = null;
        try {
            constructor = Objects.requireNonNull(cls).getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Method[] methods = Objects.requireNonNull(cls).getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase(line)) {
                Object object = null;
                try {
                    object = Objects.requireNonNull(constructor).newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                try {
                    if (numberConstructor == 1) {
                        method.invoke(object, file);
                    } else if (numberConstructor == 2) {
                        method.invoke(object, forConstructor, file);
                    } else if (numberConstructor == 3) {
                        file = (File) method.invoke(object, forConstructor, file);
                        return file;
                    } else {
                        method.invoke(object, forConstructor);
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }
}
