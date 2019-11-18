package ua.step.example.part1.array;

import java.util.Arrays;
import java.util.Random;

/**
 * 
 * Массив - нахождение максимального и минимального значения в массиве
 *
 */
public class Task11 {
	public static void main(String[] args) {
		Random rnd = new Random();
		int[] mas = new int[10];
		for (int i = 0; i < mas.length; i++) {
			mas[i] = rnd.nextInt();
		}
		System.out.println(Arrays.toString(mas));

		int max = Integer.MIN_VALUE;
		for (int value : mas) {
			if (max < value) {
				max = value;
			}
		}
		System.out.printf("Maximum: %,d", max);
		// FIXME найди минимальное значение в массиве
	}
}