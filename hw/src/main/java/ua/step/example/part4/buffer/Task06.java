package ua.step.example.part4.buffer;

/**
 * 
 * Удалаение символов из буфера строк
 *
 */
public class Task06 {
	public static void main(String[] args) {
		StringBuffer phrases = new StringBuffer("Я не люблю котов!");
		// удаление диапазона символов
		phrases.delete(2, 5); 
		System.out.println(phrases);
		// удаление символа по индексу
		phrases.deleteCharAt(phrases.length() - 1); 
		phrases.append('?');
		System.out.println(phrases);
	}
}