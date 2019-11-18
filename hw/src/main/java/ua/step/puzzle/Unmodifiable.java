package ua.step.puzzle;

/**
 * 
 * Исправь код в 13 строке чтобы вывелась только буква d
 *
 */
public class Unmodifiable
{
    public static void main(String[] args)
    {
        String s = "abcd";
        s.substring(3);
        System.out.println(s);
    }
}