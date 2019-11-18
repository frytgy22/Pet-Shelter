package ua.step.example.part4.buffer;
import java.util.StringTokenizer;

/**
 * 
 * Разбиение строки на слова (или токены)
 * 
 */
public class Task09
{
    public static void main(String[] args)
    {
        String s = "Я люблю играть с котом";
        StringTokenizer st = new StringTokenizer(s); // разбиватель строк на токены (части)
        while (st.hasMoreTokens())
        {
            System.out.println(st.nextToken());
        }
        s.split(s);
    }
}