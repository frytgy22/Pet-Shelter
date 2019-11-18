package ua.step.homework02;

import java.util.Scanner;

/**
 * Задание: Ввести с клавиатуры строку текста, а затем один символ. Показать на экран
 * индексы (через пробел) и количество совпадений на следующей строке (ищем вхождения символа в строку).
 * <p>
 * <p>
 * Если символ не найдет - выводить -1
 * <p>
 * Примечание: использовать методы строк
 * <p>
 * <p>
 * Пример:
 * Для строки "обороноспособность" и введенного символа "o" вывод будет:
 * 0 2 4 6 9 11 14
 * 7
 */
public class Task01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String string = scanner.nextLine();
        System.out.print("Введите символ: ");
        String value = scanner.nextLine();

        // TODO: Пишите код здесь

    }
}