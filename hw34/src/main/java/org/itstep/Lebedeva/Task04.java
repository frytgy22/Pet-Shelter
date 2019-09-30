package org.itstep.Lebedeva;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Задание 4
 * <p>
 * * Создайте класс Дробь (Fraction). Необходимо хранить в полях
 * * класса: числитель и знаменатель. Реализуйте методы клас-
 * * са для ввода данных, вывода данных, реализуйте доступ
 * * к отдельным полям через методы класса. Также создайте
 * * методы класса для выполнения арифметических операций
 * * (сложение (add), вычитание (minus), умножение (multiple), деление (division), и т.д.).
 * * Методы должны принимать в качестве аргумента вторую дроб и
 * * возвращать результат в виде дроби.
 * * Создайте два конструктора: по умолчанию, и с параметрами
 */
public class Task04 {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Fraction fraction1 = new Fraction(3, 15);
        Class<?> cls = fraction1.getClass();

        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(RunIt.class)) {
                RunIt runIt = method.getDeclaredAnnotation(RunIt.class);
                method.invoke(fraction1, new Fraction(runIt.a(), runIt.b()));
            }
        }
    }
}

