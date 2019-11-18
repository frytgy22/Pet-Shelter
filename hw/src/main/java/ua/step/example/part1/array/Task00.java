package ua.step.example.part1.array;

import java.util.Random;

/**
 * 
 * Псевдослучайные числа на основе класса Random
 * 
 */
public class Task00 {
	public static void main(String[] args) {
		Random rnd = new Random();
		// метод nextInt возвращает псевдослучайное число в диапазоне от
		// минимального отрицательного до максимального положительного
		int r1 = rnd.nextInt();
		System.out.printf("Случайное число: %d%n" , r1); //

		
		int r2 = rnd.nextInt(33);
		System.out.printf("Случайное число в диапазоне от 0 до 33: %d%n" , r2); //
		
		// случайное логическое значение
		System.out.printf("Случайное булевое значение: %b %n", rnd.nextBoolean());
		
		// FIXME Выведите в строку через пробел 10 случайных чисел (используйте цикл for)
	}
}