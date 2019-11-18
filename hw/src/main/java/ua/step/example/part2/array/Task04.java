package ua.step.example.part2.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * 
 * Методы класса Arrays. Сортировка массива.
 * 
 */
public class Task04 {
	/**
	 * Метод sort() из класса Arrays использует усовершенствованный алгоритм
	 * Быстрой сортировки (Quicksort), который эффективен в большинстве случаев.
	 */
	public static void main(String[] args) {
		// заполнение массива одинаковым значением
		int[] mas = new int[10];
		// класс для получения псевдослучайных значений
		Random rnd = new Random(); 
		for (int i = 0; i < mas.length; i++) {
			mas[i] = rnd.nextInt(100);
		}
		System.out.println(Arrays.toString(mas));

		// сортировка массива в натуральном порядке
		Arrays.sort(mas);
		System.out.println(Arrays.toString(mas));

		String[] names = { "Аня", "Маня", "Глаша", "Юля" };
		Arrays.sort(names, Collections.reverseOrder()); // только для ссылочных
														// типов
		// System.out.println(Arrays.toString(names));
		// Arrays.sort(mas, Collections.reverseOrder());

		Integer[] mas11 = new Integer[] { 3, 1, 4, 6, 2 };
		// сортировка в обратном порядке, возможна только дла классов-оберток
		// примитивных типов
		Arrays.sort(mas11, Collections.reverseOrder());
		// System.out.println(Arrays.toString(mas11));
	}
}
