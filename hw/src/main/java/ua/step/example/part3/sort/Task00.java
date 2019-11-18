package ua.step.example.part3.sort;

/**
 * 
 * Определение времени работы алгоритма
 *
 */
public class Task00 {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// метод currentTimeMillis возвращает количество милисекунд прошедших с
		// начала 1 января 1970 года
		long startTime = System.currentTimeMillis();
		System.out.println("start: " + startTime + " ms");

		// количество наносекунд из системного таймера
		long startTimeNano = System.nanoTime();
		System.out.println("start: " + startTimeNano + " ns");

		final int n = 10000;
		for (int i = 0; i < n; i++) {
			int[] t = new int[n];
		}
		long endTime = System.currentTimeMillis();
		System.out.println("end  : " + endTime + " ms");

		long workTime = endTime - startTime;
		System.out.println(workTime + " ms");
		System.out.println(System.nanoTime() - startTimeNano + " ns");

	}
}
