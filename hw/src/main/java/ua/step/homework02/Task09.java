package ua.step.homework02;

import java.util.Scanner;

/**
 * Задание: Написать программу проверяющую является ли одна строка, анаграммой для другой
 * строки (строка может состоять из нескольких слов и символов пунктуации).
 * Пробелы и пунктуация должны игнорироваться при анализе. Разница в больших и
 * маленьких буквах должна игнорироваться. Обе строки должны вводиться с
 * клавиатуры. Программа должна выводить Yes если строки являются анаграммой и
 * No иначе.
 *
 * Пример:
 * Если введены две строки "растение" и "старение",
 * то вывод будет : Yes
 *
 * @see <a href='https://ru.wikipedia.org/wiki/%D0%90%D0%BD%D0%B0%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%B0'>Анаграмма</a>
 */

public class Task09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите две строки: ");
        String line1 = scanner.nextLine();
        String line2 = scanner.nextLine();

        // TODO: Пишите код здесь
    }
}
