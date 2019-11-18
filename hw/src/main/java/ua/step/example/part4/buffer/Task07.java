package ua.step.example.part4.buffer;
/**
 * 
 * StringBuilder
 *
 */
public class Task07
{
    public static void main(String[] args)
    {
        StringBuilder sb = new StringBuilder("Кошки это - ");
        sb.append("тепло, ");
        sb.append("добро,").append(" ").append("ласка.");
        System.out.println(sb);
    }
}