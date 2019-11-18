package ua.step.example.part3.regexp;

/**
 * 
 * Замена части строки используя регулярное выражение
 *
 */
public class Task04 {
	public static void main(String[] args) {

		final String TEXT = "Мне очень нравится Тайланд. Таиланд - это то место куда бы я поехал. тайланд - мечты сбываются!";
		String regexp = "[Тт]а[ий]ланд";
		System.out.println(TEXT.replaceAll(regexp, "Украина"));
	}
}