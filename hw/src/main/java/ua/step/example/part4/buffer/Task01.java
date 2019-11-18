package ua.step.example.part4.buffer;

/**
 * Добавление объектов в строчный буфер 
 */
public class Task01
{
    public static void main(String[] args)
    {
        StringBuffer sb = new StringBuffer("test");
        //sb = "test";
        System.out.println(sb);

        sb.append('-').append("test");
        sb.append(true);
        sb.append(1);
        System.out.println(sb);

        String s1  = sb.toString();
        System.out.println(s1);

        System.out.println(sb.length()); // количество символов в строке
    }
}
