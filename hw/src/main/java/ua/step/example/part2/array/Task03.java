package ua.step.example.part2.array;

import java.util.Arrays;

/**
 * 
 * Методы класса Arrays. Заполнение массива одинаковыми значениями.  
 * 
 */
public class Task03 {
	public static void main(String[] args) {
		// заполнение массива одинаковым значением
		int[] mas = new int[10];
		Arrays.fill(mas, 1);
		System.out.println(Arrays.toString(mas));

		int startIndex = 1;
		int endIndex = 4;
		// второй и третий параметр указывают начало и конец заполняемого отрезка
		Arrays.fill(mas, startIndex, endIndex, 2);
		//System.out.println(Arrays.toString(mas));
	}
}
