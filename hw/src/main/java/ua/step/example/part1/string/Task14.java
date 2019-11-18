package ua.step.example.part1.string;

/**
 * 
 * Поиск в строке
 *
 */
public class Task14 {
	public static void main(String[] args) {
		String cat = "котёнок";
		// возвращает индекс первого найденого совпадения
		int index = cat.indexOf('о'); 
										
		System.out.println("Буква о найдена под индексом: " + index);

		// возвращает индекс совпадения (начиная поиск с указаного индекса).
		// Поиск слева направо
		index = cat.indexOf('о', 2);
		System.out.println("Буква о найдена под индексом: " + index);

		// можно искать подстроку
		index = cat.indexOf("кот");
		System.out.println("Строка кот найдена под индексом: " + index);

		index = cat.indexOf("кот", index + 1);
		System.out.println("Строка кот не найден поэтому индекс отрицательный: " + index);
	}
}