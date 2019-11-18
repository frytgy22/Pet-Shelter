package ua.step.example.part3.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * Пример использования регулярных выражений для проверки корректности вводимого имени
 *
 */
public class Task06 {
	public static void main(String[] args) {
		System.out.println("Cool check:");

		System.out.println(checkWithRegExp("_@BEST"));
		System.out.println(checkWithRegExp("vovan"));
		System.out.println(checkWithRegExp("vo"));
		System.out.println(checkWithRegExp("Z@OZA"));

		System.out.println("\nDumb check:");

		System.out.println(dumbCheck("_@BEST"));
		System.out.println(dumbCheck("vovan"));
		System.out.println(dumbCheck("vo"));
		System.out.println(dumbCheck("Z@OZA"));
	}

	/**
	 * Проверка с использования регулярных выражений
	 * 
	 */
	public static boolean checkWithRegExp(String userNameString) {
		Pattern p = Pattern.compile("^[a-z0-9_-]{3,15}$");
		Matcher m = p.matcher(userNameString);
		return m.matches();
	}

	/**
	 * Проверка алгоритмом
	 */
	public static boolean dumbCheck(String userNameString) {

		char[] symbols = userNameString.toCharArray();
		if (symbols.length < 3 || symbols.length > 15)
			return false;

		String validationString = "abcdefghijklmnopqrstuvwxyz0123456789_";

		for (char c : symbols) {
			if (validationString.indexOf(c) == -1)
				return false;
		}
		return true;
	}
}