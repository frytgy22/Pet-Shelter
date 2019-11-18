package ua.step.example.part1.string;

/**
 * 
 * Escape последовательности
 *
 */
public class Task02 {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String s = "a\n + \tb";
		System.out.println(s);
		s = "\"Коты не спрашивают, они просто берут все, что им надо.\"";
		// System.out.println(s);

		String text = "\"Символ, которому предшествует наклонная черта влево (\\), "
				+ "является escape-последовательностью и имеет особое значение в компиляторе.\"";
		// System.out.println(text);
	}
}
