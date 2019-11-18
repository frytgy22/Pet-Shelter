package ua.step.example.part2.mstatic;

/**
 * 
 * Различные способы преобразования целого числа в строку.  
 *
 */
public class Task06 {
	public static void main(String[] args) {

		int integerVariable = 10;
		// конкатенация с пустой строкой (создается три строки)
		String first = integerVariable + ""; 
		System.out.println(first);
		
		// вызов статического метода valueOf класса String
		String second = String.valueOf(integerVariable); 
		System.out.println(second);
		
		// вызов статического метода toString класса-обертки
		String third = Integer.toString(integerVariable); 
		System.out.println(third);
	}
}