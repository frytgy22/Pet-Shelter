package ua.step.homework02;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Задание:
 * Для проверки остаточных знаний учеников после летних каникул, учитель младших
 * классов решил начинать каждый урок с того, чтобы задавать каждому ученику
 * пример из таблицы умножения, но в классе 15 человек, а примеры среди них не
 * должны повторяться. В помощь учителю напишите программу, которая будет
 * выводить на экран 15 случайных примеров из таблицы умножения (от 2*2 до 9*9,
 * потому что задания по умножению на 1 и на 10 — слишком просты). При этом
 * среди 15 примеров не должно быть повторяющихся (примеры 2*3 и 3*2 и им
 * подобные пары считать повторяющимися). Пример вывода 6 * 4 = 24. Каждый
 * пример в новой строке.
 * <p>
 * Пример вывода:
 * <p>
 * 1 * 9 = 9
 * 10 * 8 = 80
 * 6 * 4 = 24
 * 2 * 2 = 4
 * 10 * 5 = 50
 * 8 * 8 = 64
 * 4 * 3 = 12
 * 6 * 5 = 30
 * 2 * 1 = 2
 * 4 * 9 = 36
 * 5 * 8 = 40
 * 3 * 1 = 3
 * 6 * 6 = 36
 * 3 * 3 = 9
 * 9 * 6 = 54
 */
public class Task12 {
    public static void main(String[] args) {
        // TODO: не менять стоки ниже - нобходимо для тестирования @see ua.step.homework01.TaskTest12
        long seed = args.length > 0 ? Long.parseLong(args[0]) : LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);

        // Использовать для генерирования элементов массива
        Random rnd = new Random(seed);

        Scanner scanner = new Scanner(System.in);

        // TODO: Пишите код здесь
        class Eq implements Comparable<Eq> {
            private int first;
            private int second;

            public Eq(int first, int second) {
                this.first = first;
                this.second = second;
            }

            @Override
            public String toString() {
                return String.format("%d * %d = %d", first, second, first * second);
            }

            @Override
            public int compareTo(Eq o) {
                int diff = (first - o.first) + (second - o.second);
                return diff == 0 ? ((second - o.first) + (first - o.second)) : diff;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Eq eq = (Eq) o;
                return (first == eq.first &&
                        second == eq.second) ||
                        (second == eq.first &&
                                first == eq.second);
            }

            @Override
            public int hashCode() {
                return Objects.hash(first, second) + Objects.hash(second, first);
            }
        }
        rnd.setSeed(0);
        Stream.generate(() -> new Eq(rnd.nextInt(10) + 1, rnd.nextInt(10) + 1))
                .distinct()
                .limit(15)
                .forEach(System.out::println);
    }
}
