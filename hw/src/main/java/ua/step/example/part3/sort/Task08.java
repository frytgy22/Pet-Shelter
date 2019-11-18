package ua.step.example.part3.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Сортировка слиянием. Пример.
 */
public class Task08 {
	public static void main(String[] args) {
		Random rnd = new Random();
		int n = 10;
		int[] mas = new int[n];
		for (int i = 0; i < n; i++) {
			mas[i] = rnd.nextInt(n);
		}

		System.out.println(Arrays.toString(mas));

		sortMergeNoRecursive(mas); // сортировка

		System.out.println(Arrays.toString(mas));
		// FIXME посчитать время потраченное на сортировку слиянием
	}

	private static void sortMergeNoRecursive(int[] array) {
		final int lenght = array.length;
		int n = 1; // кратность сравнений (сравнивать по 1-му елем., 2-м ...)
		int shift; // сдвиг в перебираемом массиве
		int twoSize; // количество елементов для 2-го массива
		int[] arr2;
		while (n < lenght) {
			shift = 0;
			while (shift < lenght) {
				if (shift + n >= lenght) {
					break;
				}
				twoSize = (shift + n * 2 > lenght) ? (lenght - (shift + n)) : n;
				arr2 = merge(Arrays.copyOfRange(array, shift, shift + n),
						Arrays.copyOfRange(array, shift + n, shift + n + twoSize));
				for (int i = 0; i < n + twoSize; ++i) {
					array[shift + i] = arr2[i]; // замена на отсортированное
				}
				shift += n * 2;
			}
			n *= 2;
		}
	}

	private static int[] merge(int[] array1, int[] array2) {
		int lenght1 = array1.length;
		int lenght2 = array2.length;
		int a = 0;
		int b = 0;
		int len = lenght1 + lenght2; // a, b - счетчики в массивах
		int[] result = new int[len];
		for (int i = 0; i < len; i++) {
			if (b < lenght2 && a < lenght1) {
				if (array1[a] > array2[b]) {
					result[i] = array2[b++];
				} else {
					result[i] = array1[a++];
				}
			} else if (b < lenght2) {
				result[i] = array2[b++];
			} else {
				result[i] = array1[a++];
			}
		}
		return result;
	}
}
