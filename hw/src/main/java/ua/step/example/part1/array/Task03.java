package ua.step.example.part1.array;

/**
 * 
 * Инициализация в месте объявления
 *
 */
public class Task03 {
	public static void main(String[] args) {
		int[] numbers = new int[] { 3, 2, 1 };

		// получение значения элемента массива по индексу и присвоения в переменную
		int a = numbers[1];
		System.out.println("Значение элемента под индексом 1: " + a);
		System.out.printf("Значение элемента под индексом %d: %d %n", a, numbers[a]);

		// использование значений элементов массива  в выражениях 
		a = numbers[0] - numbers[1] * numbers[2];
		System.out.printf("Значение элемента под индексом %d: %d %n", a, numbers[a]);
	}
}
