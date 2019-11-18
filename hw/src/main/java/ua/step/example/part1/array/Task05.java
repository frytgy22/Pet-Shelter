package ua.step.example.part1.array;

import java.util.Arrays;

/**
 * 
 * Использование for для перебора элементов массива (foreach)
 *
 */
public class Task05 {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// инициализация массива
		int[] numbers = new int[] { 3, 2, 1 };

		// вывод значений элементов массива с помошью цикла foreach
		System.out.println();
		for (int number : numbers) {
			System.out.print(number + " ");
		}
		System.out.println();

		// foreach можно использовать только для чтения значений.
		for (int number : numbers) {
			number = 3; // значение записано в копию элемента, но не в массив.
		}
		System.out.println(Arrays.toString(numbers));

		// FIXME создайте массив на 10 элементов и заполните его случайными
		// числами от -10 до 10. Выведите значения массива на экран. 
		// Выведи сумму элементов массива.
	}
}
