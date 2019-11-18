package ua.step.example.part3.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Бинарный поиск
 */
public class Task03 {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Random rnd = new Random();
		int n = 10;
		int[] mas = new int[n];
		for (int i = 0; i < n; i++) {
			mas[i] = rnd.nextInt(n);
		}

		System.out.println(Arrays.toString(mas));
		Scanner scanner = new Scanner(System.in);
		System.out.println("Введите искомое число");
		int x = scanner.nextInt();

		// FIXME написать код линейного поиска и подсчитать время потраченное на
		// поиск. Не менять код написанный выше.
		// Программа должна вывести индекс элемента значение которого совпадает
		// с введенным с клавиатуры
		scanner.close();
	}
}