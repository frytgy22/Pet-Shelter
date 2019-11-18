package ua.step.example.part4.buffer;

/**
 * Сравнение объектов класса StringBuffer или StringBuildr
 */
public class Task08 {
	public static void main(String[] args) {
		StringBuffer sb1 = new StringBuffer("abc");
		StringBuffer sb2 = new StringBuffer("abc");
		System.out.println(sb1 == sb2);
		System.out.println(sb1.equals(sb2)); // нельзя сравнивать два строковых буфера
		// System.out.println(sb1.toString().equals(sb2.toString()));
	}
}
