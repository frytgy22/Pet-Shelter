package ua.step.homework02;

import java.util.Scanner;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 
 * Задание: Написать программу которая создаст строку в которой все целые числа начиная с
 * 1 выписаны в одну строку «123456789101112131415...». В строке входных данных
 * записано целое число n (1 ≤ n ≤ 1000) — где n позиция цифры, которую вам
 * требуется вывести. По данному числу n выведите цифру на n-й позиции
 * (используется нумерация с 1).
 *
 * Пример:
 * Для позиции n = 10 вывод будет 1
 * Для позиции n = 11 вывод будет 0
 * Для позиции n = 12 вывод будет 1
 */
public class Task02 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Введите номер позиции: ");
		int position = scanner.nextInt();

		// TODO: Пишите код здесь



	}
}
