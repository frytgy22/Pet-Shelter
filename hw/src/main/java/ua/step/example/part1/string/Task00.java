package ua.step.example.part1.string;

/**
 * 
 * Объявление и инициализация переменной типа String
 *
 */
public class Task00
{
    public static void main(String[] args)
    {
    	String s = null; // строка является ссылочным типом
    	
        // TODO только ссылочные переменные могут принимать значение литерала null
        // int i = null; // ошибка так как int примитивный тип, а не ссылочный.
    	
    	System.out.println(s);
        // инициализация строковым литералом
    	String aboutCat = "Кот - это звучит гордо, а если наступить на хвост, то еще и громко!";
        System.out.println(aboutCat);
    }
}