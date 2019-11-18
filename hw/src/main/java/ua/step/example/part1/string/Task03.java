package ua.step.example.part1.string;
/**
 * 
 * Объединение (канкатинация) строк
 *
 */
public class Task03
{
    public static void main(String[] args)
    {
        String cat = "Кот"; // лиетрал строки
        String name = "Васька"; 
        String fullname = cat + " " + name;  // конкатинация строк
        System.out.println(fullname);

        fullname = cat.concat(name);
        System.out.println(cat); // исходная строка не изменилась
        System.out.println(fullname);
    }
}