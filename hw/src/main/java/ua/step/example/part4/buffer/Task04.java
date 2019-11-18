package ua.step.example.part4.buffer;
/**
 * 
 * Вставка символа или строки в строчный буфер 
 *
 */
public class Task04
{
    public static void main(String[] args)
    {
        StringBuffer sb = new StringBuffer("Я котов!"); 
        sb.insert(2, "люблю "); 
        System.out.println(sb.toString());
    }
}
