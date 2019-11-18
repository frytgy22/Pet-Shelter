package ua.step.example.part3.regexp;

/**
 * 
 * Замена части строки используя регулярное выражение
 *
 */
public class Task01 {
	public static void main(String[] args) {

		final String TEXT = "11 1aaa$$$b  bb___";
		String regexp = "\\D"; //Нецифровой символ
		//String regexp = "\\d";// Цифровой символ
		//String regexp = "\\w"; //Буквенный или цифровой символ или знак подчёркивания
		//String regexp = "\\s"; // Пробельный символ
		//String regexp = "\\S"; // НЕ gробельный символ
		//String regexp = "^1"; //Начало текста
		
		System.out.println(TEXT.replaceAll(regexp, "#"));
	}
}