package ua.step.example.part2.mstatic;

import java.util.Locale;

/**
 * 
 * Форматирование данных в строке
 *
 */
public class Task04 {
	public static void main(String[] args) {
		// % — специальный символ, обозначающий начало инструкций
		// форматирования.
		System.out.println("Форматирование целых чисел");
		System.out.printf("%d\n", 1);

		// Число менее 3 знаков будет «подвинуто» вправо на недостающее
		// количество знаков и дополнено нулями.
		System.out.printf("%03d\n", 2);

		System.out.println("Вывод целого числа с разделением разрядов");
		System.out.printf("%,d\n", 784565465);

		System.out.println("\nФорматирование чисел с плавающей точкой");
		// Автоматическое округление до 6 знаков после запятой.
		System.out.printf("%f\n", Math.E);
		
		// Число будет выведено с 2 знаками после запятой после округления.
		System.out.printf("%.2f\n", 1.5555555); //

		int i = 675;
		double root = Math.sqrt(i);
		System.out.printf("Корень числа %d равен %f\n", i, root);

		/*
		 * f — указывает на то, что выводим число с плавающей точкой. .10 —
		 * выведенное число будет содержать 10 знаков после запятой. 20 — всего
		 * выведенное число будет иметь ширину в 20 символов +0 — недостающие
		 * (до 20-ти) символы будут заполнены нулями, перед числом будет указан
		 * знак (+) 1$ — данный формат применяется к первому аргументу, который
		 * находится после строки форматирования.
		 */
		System.out.printf("%1$+020.10f\n", Math.PI);

		System.out.println("\nЛокализация");
		System.out.printf(Locale.ENGLISH, "%,d%n", 1000000);// 1,000,000
		System.out.printf(Locale.GERMAN, "%,d%n", 1000000); // 1.000.000
		System.out.printf(Locale.FRANCE, "%,d%n", 1000000); // 1

		String s = String.format("Курс валют: %-4s%-8.4f  %-4s%-8.4f", "USD", 58.4643, "EUR", 63.3695);
		System.out.println(s);


	}
}
