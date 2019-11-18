package ua.step.example.part2.array;

import java.util.Arrays;

/**
 * 
 * Печать массивов
 *
 */
public class Task02 {
	public static void main(String[] args) {
		int[] mas1 = new int[] { 1, 2, 3 };
		System.out.println(mas1);
		System.out.println(Arrays.toString(mas1)); // правильно
		
		boolean[] mas2 = new boolean[] { true, false, true };
		System.out.println(mas2); // не правильно 
		System.out.println(Arrays.toString(mas2)); // правильно
		
		char[] c = new char[] { 49, 50, 51 };
		System.out.println(c); // работает только для массива символов 
		System.out.println(Arrays.toString(c)); 
		
		int[][] mas3 = new int[][] {{ 1, 2, 3 }, { 3, 2, 1 }};
		System.out.println(Arrays.toString(mas3)); // не правильно
		System.out.println(Arrays.deepToString(mas3)); // правильно
	}
}
