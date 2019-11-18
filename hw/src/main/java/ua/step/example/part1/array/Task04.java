package ua.step.example.part1.array;

/**
 * 
 * Чтение значение элементов массива.
 *
 */
public class Task04 {
	public static void main(String[] args) {
		int[] numbers = new int[] { 3, 2, 1 };

		// получение значения элемента массива по индексу и присвоения в переменную
		int a = numbers[1];
		System.out.println(a);
		// System.out.println(numbers[a]);

		// использование полученых значений в выражениях 
		a = numbers[0] - numbers[1] * numbers[2];
		// System.out.println(numbers[a]);

		// вывод значений элементов массива с помошью цикла for
		for (int i = 0; i < numbers.length; i++) {
			System.out.printf(" %d", numbers[i]);
		}
		System.out.println();
		System.out.println(numbers); // для вывода элементов не годится 
	}
}