package ua.step.example.part1.string;
/**
 * 
 * Проверка содержит ли строка подстроку.
 *
 */
public class Task08
{
    public static void main(String[] args)
    {
        String testString = "котёнок";
        if (testString.contains("кот"))
        {
            System.out.println("Котёнок это маленький кот!");
        }
        
        if (testString.contains("Кот"))
        {
            System.out.println("Котёнок все же маленький кот!");
        }
    }
}
