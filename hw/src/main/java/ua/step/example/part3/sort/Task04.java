package ua.step.example.part3.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Бинарный поиск
 */
public class Task04 {
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

		// **********************************************************************
		int index = Arrays.binarySearch(mas, x); // проверочный метод
		System.out.println("Проверочный индекс = " + index);
		// **********************************************************************
		Arrays.sort(mas); // сортировка
		// FIXME написать код бинарного поиска и подсчитать время потраченное на
		// поиск. Не менять код написанный выше.
		// Программа должна вывести индекс элемента значение которого совпадает
		// с введенным с клавиатуры

		int left = 0;
		int right = mas.length - 1;
		
		scanner.close();
	}
}