package ua.step.example.part1.string;

/**
 * 
 * Удаение начальных и конечных пробелов
 *
 */
public class Task18 {
	public static void main(String[] args) {
		String cat = "     кот в пробелах        ";
		System.out.println("_" + cat.trim() + "_");
	}
}