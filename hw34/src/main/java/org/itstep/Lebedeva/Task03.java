package org.itstep.Lebedeva;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 3. Дополнительное задание
 * Написать тест класса из предыдущего домашнего задания в отсутвии такового класса (тест должен компилироваться, т.е. используя рефлексию)
 */
public class Task03 {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        try {
            Class<?> cls = Class.forName("org.itstep.Lebedeva.Fraction");
            Object fraction1 = cls.getDeclaredConstructor(new Class[]{int.class, int.class}).newInstance(3, 15);//first fraction for comparison
            Method[] methods = cls.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(RunIt.class)) {
                    RunIt runIt = method.getDeclaredAnnotation(RunIt.class);
                    Object fraction2 = cls.getDeclaredConstructor(new Class[]{int.class, int.class}).newInstance(runIt.a(), runIt.b());//second fraction for comparison
                    if (method.getName().equals("addition")) {
                        if (!method.invoke(fraction1, fraction2).equals(cls.getDeclaredConstructor(new Class[]{int.class, int.class}).newInstance(19, 45))) {//comparing the result
                            System.err.print("Error.\nExpected: 3/15 + 4/18 = 19/45\n");
                        }
                    }
                    if (method.getName().equals("subtraction")) {
                        if (!method.invoke(fraction1, fraction2).equals(cls.getDeclaredConstructor(new Class[]{int.class, int.class}).newInstance(1, 5))) {
                            System.err.print("Error.\nExpected: 3/15 - 0/2 = 1/5\n");
                        }
                    }
                    if (method.getName().equals("multiplication")) {
                        if (!method.invoke(fraction1, fraction2).equals(cls.getDeclaredConstructor(new Class[]{int.class, int.class}).newInstance(3, 20))) {
                            System.err.print("Error.\nExpected: 3/15 * 3/4 = 3/20\n");
                        }
                    }
                    if (method.getName().equals("division")) {
                        if (!method.invoke(fraction1, fraction2).equals(cls.getDeclaredConstructor(new Class[]{int.class, int.class}).newInstance(1, 4))) {
                            System.err.print("Error.\nExpected: 3/15 : 4/5 = 1/4\n");
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("The class not found.");
        }
    }
}

