package ua.step.example.part1.string;

/**
 * 
 * Преобразование регистра букв
 *
 */
public class Task16 {
	public static void main(String[] args) {
		String source = "кОтЕнОк";
		System.out.println(source.toLowerCase()); // уменьшение регистра всех букв 
		System.out.println(source.toUpperCase()); // увеличения регистра всех букв
		System.out.println(source);
		
		for (int i = 0; i < source.length(); i++)
		{
			char symbol = source.charAt(i);
			System.out.print(" " + Character.isLowerCase(symbol));
		}
	}
}