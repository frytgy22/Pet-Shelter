package ua.step.example.part2.mstatic;
/**
 * 
 * Преобразование примитивных типв в строчное представление
 *
 */
public class Task01
{
    @SuppressWarnings("unused")
	public static void main(String[] args)
    {
        // вызов статического метода valueOf
    	String s1 = String.valueOf(33); 
        System.out.println(s1);

        // из строки в целое число типа int
        int i = Integer.parseInt(s1);

        System.out.println(String.valueOf(2 + 2));

        s1 = String.valueOf(1.5f);
        System.out.println(s1);
        
        System.out.println(String.valueOf(true ^ true));
    }
}