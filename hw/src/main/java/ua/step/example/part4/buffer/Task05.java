package ua.step.example.part4.buffer;

/**
 * 
 * Изменение порядка символов на обратный
 *
 */
public class Task05 {
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("МОКНЕТ ОКСАНА С КОТЕНКОМ");
		sb.reverse(); // 
		sb.insert(6, " ").deleteCharAt(9).deleteCharAt(10).insert(13, " ").insert(15, " ").deleteCharAt(18);
		System.out.println(sb);
	}
}