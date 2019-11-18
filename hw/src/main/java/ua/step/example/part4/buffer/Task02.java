package ua.step.example.part4.buffer;

/**
 * Добавление объектов в строчный буфер
 */
public class Task02 {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		for (char c = 'A'; c < 'Z'; c++) {
			sb.append(c);
		}
		System.out.println(sb);
	}
}
