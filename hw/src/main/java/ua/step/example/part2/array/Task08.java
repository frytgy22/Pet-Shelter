package ua.step.example.part2.array;

import java.util.Random;

/**
 * 
 * Вывод двухмерного массива в виде отформатированной таблицы
 *
 */
public class Task08 {
	public static void main(String[] args) {
		final int n = 10;
		final int RANGE = 10000; // измени диапазон до 100 и 1000
		int[][] mas = new int[n][n]; // создание квадратного двухмерного массива
		Random rnd = new Random();
		// заполнение массиваы
		for (int i = 0; i < mas.length; i++) {
			for (int j = 0; j < mas[i].length; j++) {
				mas[i][j] = rnd.nextInt(RANGE);
			}
		}
		
		// вывод массива на консоль в виде таблицы
		int digit = (int) Math.ceil(Math.log10(RANGE));
		for (int i = 0; i < mas.length; i++) {
			for (int j = 0; j < mas[i].length; j++) {
				System.out.printf("%" + digit + "d ", mas[i][j]);
				//System.out.printf("%-" + digit + "d ", mas[i][j]); // выравнивание по левому краю
			}
			System.out.println();
		}
	}
}
