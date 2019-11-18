package ua.step.example.part1.string;

/**
 * 
 * Получение подстроки из строки
 *
 */
public class Task13 {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String string = "Клекот";
		// возвращает подстроку начиная с 3 индекса (4 символа) включительно
		String world = string.substring(3); 
		System.out.println(world);
		
		// FIXME получи слово стратегия из строки ниже
		String strategy = "контрстратегия";

		String cat = "скотина";
		// возвращает подстроку начиная с 1 индекса включительно(2 символа) и до
		// 4 индекса исключительно
		System.out.println(cat.substring(1, 4));
		
		// FIXME получи слово граф из строки ниже
		String graf = "рентгеноэлектрокардиографический";
	}
}
