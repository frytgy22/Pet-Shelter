package ua.step.example.part2.array;

/**
 * 
 * Многомерные массивы. Отложенная инициализация
 * 
 */
public class Task01 {
	public static void main(String[] args) {
		// отложенная инициализация
		int[][] mas = new int[5][];
		for (int i = 0; i < mas.length; i++) {
			mas[i] = new int[i]; // присвоение значения первого элемента
		}
		
		// вывод значений двухмерного ступенчатого массива 
		for (int i = 0; i < mas.length; i++) {
			for (int j = 0; j < mas[i].length; j++) {
				System.out.printf("%2d ", mas[i][j]);
			}
			System.out.println();
		}
	}
}