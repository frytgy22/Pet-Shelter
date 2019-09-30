package org.itstep.Lebedeva;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Field;

/**
 * 2. Создать аннотацию Value, которая принимает строковый аргумент.
 * Аннотацию можно применять к полям класса.
 * <p>
 * Создать класс, в котором применить аннотацию к одному или нескольким полям:
 * <p>
 * class Service {
 *
 * @Value("db.properties") private String properties;
 * ...
 * <p>
 * }
 * <p>
 * Дополнить класс Service нужными геттерами и сеттерами, переопределить метод toString.
 * Примечание: поле класса помеченное аннотацией Value НЕ инициализировать к констукторах.
 * <p>
 * Написать код, который позволит создать класс Service, и присвоить значение аргумента аннотации
 * Value соответсвующему полю класса.
 * <p>
 * Привести пример применения кода.
 */
public class Task02 {
    public static void main(String[] args) throws IllegalAccessException {
        Service service = new Service("dp", 20000);
        System.out.println("Before: " + service);

        Class<?> serviceClass = service.getClass();
        Field[] fields = serviceClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Value.class)) {
                Value value = field.getDeclaredAnnotation(Value.class);
                field.setAccessible(true);
                field.set(service, value.value());
            }
        }
        System.out.println("After: " + service);
    }
}

@Data
@RequiredArgsConstructor
class Service {
    @NonNull
    private String nameService;
    @Value("dp.properties")
    private String properties;
    @NonNull
    private double cost;
    @Value("org.it")
    private String webSite;
}
