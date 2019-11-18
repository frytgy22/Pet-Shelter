package ua.step.example.part2.array;

/**
 * 
 * Использование массивов при работе с строками
 *
 */
public class Task17 {
	public static void main(String[] args) {
		String str = "1 000 000 000";
		// преобразовываем строку в массив
		char[] chArray = str.toCharArray();
		// перебираем все элементы массива
		for (int i = 0; i < chArray.length; i++) {
			// находим пробел
			if (chArray[i] == ' ') {
				// заменяем на точку
				chArray[i] = '.';
			}
		}
		// преобразуем массив обратнов строку
		String strNew = String.valueOf(chArray);
		// выводим результат
		System.out.println(strNew);
	}
}