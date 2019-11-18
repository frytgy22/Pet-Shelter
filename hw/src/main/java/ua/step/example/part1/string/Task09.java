package ua.step.example.part1.string;
/**
 * 
 * Проверка начинается/заканчивается строка на подстроку.
 * 
 */
public class Task09
{
    public static void main(String[] args)
    {
        String str1 = "котлета";
        if (str1.startsWith("кот"))
        {
            System.out.println("Слово начинается с кота");
        }
        else
        {
            System.out.println("Плохое слово. Нет смысла его использовать");
        }

        String str2 = "Суккот";
        if (str2.endsWith("кот"))
        {
            System.out.println("Слово заканчивается на котике");
        }
        else
        {
            System.out.println("Плохое слово. Нет смысла его использовать");
        }
    }
}
