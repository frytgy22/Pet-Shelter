package org.itstep.Lebedeva;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 1. Создать аннотацию RunIt применимую к методу, которая принимает параметры для метода.
 * Написать код, который вызовет метод, помеченный этой аннотацией, и передаст параметры аннотации в вызываемый метод.
 *
 * @RunIt(a=2, b=5)
 * public void test(int a, int b) {...}
 */
public class Task01 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> cls = Addition.class;
        Constructor<?> constructor = cls.getDeclaredConstructor();
        Method[] methodsDeclared = cls.getDeclaredMethods();
        for (Method method : methodsDeclared) {
            if (method.isAnnotationPresent(RunIt.class)) {
                RunIt runIt = method.getDeclaredAnnotation(RunIt.class);
                int mod = method.getModifiers();
                if (Modifier.isStatic(mod)) {
                    method.invoke(null, runIt.a(), runIt.b());
                } else {
                    Object object = constructor.newInstance();
                    method.invoke(object, runIt.a(), runIt.b());
                }
            }
        }
    }
}

class Addition {
    @RunIt(a = 2, b = 5)
    public void test(int a, int b) {
        System.out.println("a + b = " + (a + b));
    }
}