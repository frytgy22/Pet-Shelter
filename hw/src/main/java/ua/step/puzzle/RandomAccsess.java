package ua.step.puzzle;

/**
 * 
 * Заполнение массива суммой индексов
 *
 */
public class RandomAccsess {
	public static void main(String[] args) {
		final int N = 10000;
		int[][] mas = new int[N][N];
		long startTime = System.nanoTime();
		for (int i = 0; i < mas.length; i++) {
			mas[i][i] = 2 * i;
			for (int j = 0; j < i; j++) {
				mas[j][i] = i + j;
				mas[i][j] = mas[j][i];
			}
		}
		System.out.println(System.nanoTime() - startTime);

		startTime = System.nanoTime();
		for (int i = 0; i < mas.length; i++) {
			mas[i][i] = 2 * i;
			for (int j = 0; j < mas[i].length; j++) {
				mas[j][i] = i + j;
			}
		}
		System.out.println(System.nanoTime() - startTime);
	}
}