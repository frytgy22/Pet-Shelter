package ua.step.example.part1.string;

/**
 * 
 * Сравнение строк
 * 
 */
public class Task10 {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String str1 = "Кот";
		String str2 = "Кошка";
		if (str1.equals(str2)) {
			System.out.println("Строки совпадают");
		} else {
			System.out.println("Строки не совпадают");
		}

		String s1 = "кот";
		String s2 = "кот";
		String s22 = "кот ".trim();
		// System.out.println(s1 == s2);
		// System.out.println(s1.equals(s2));
		//System.out.println(s1 == s22);
		//System.out.println(s1.equals(s22));

		String s3 = new String("кот");
		String s4 = new String("кот");

		// System.out.println(s3 == s4);
		// System.out.println(s3.equals(s4));

		// s3 = s3.intern();
		// System.out.println(s3 == s1);

		// System.out.println(s3 == s1);
		// System.out.println(s3.equals(s1));

		str2 = "кот";
		// System.out.println(str2.equals(str1));
		// System.out.println(str2.equalsIgnoreCase(str1));
	}
}
