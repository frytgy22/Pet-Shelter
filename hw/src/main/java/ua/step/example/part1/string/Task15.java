package ua.step.example.part1.string;

/**
 * 
 * Поиск в строке
 *
 */
public class Task15 {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String readme = "read.me.txt";
		// поиск справа-налево
		int index = readme.lastIndexOf(".");
		// вырезаем с ночала и до найденого совпадения
		System.out.println(readme.substring(0, index));

		// FIXME написать программу выводящую индексы для всех букв 'о' в слове
		// ниже, количество итераций должно совподать с количеством букв о в слове 
		String shield = "обороноспособность";

		// FIXME найти с какой буквы слово монстр содержится в указаном слове
		String fobia = "гиппопотомомонстросесквиппедалиофобия"; //Это слово обозначает навязчивую боязнь ДЛИННЫХ СЛОВ.
		
		//FIXME написать программ считающее количество повторений "це" в указаном слове
		String cece= "цецецница"; //это коробочка для содержания мух Цеце
	}
}