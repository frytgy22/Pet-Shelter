package ua.step.example.part2.array;

import java.util.Arrays;

/**
 * 
 * Методы класса Arrays. Сравнение двух массивов по значению элементов
 * 
 */
public class Task05 {
	public static void main(String[] args) {
		int[] mas1 = { 1, 2, 3 };
		int[] mas2 = { 1, 2, 3 };
		System.out.printf("Сравнение ссылок: %b %n", mas1 == mas2);
		
		// поэлементное сравнение массивов
		boolean isEqual = Arrays.equals(mas1, mas2);
		System.out.printf("Сравнение содержимого: %b %n", isEqual);

		// хэш-код массива
		System.out.println("h1 = " + Arrays.hashCode(mas1) + " h2 = " + Arrays.hashCode(mas1));
	}
}
