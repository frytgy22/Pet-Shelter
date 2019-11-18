package ua.step.example.part1.array;

import java.util.Arrays;

/**
 * 
 * Установка и чтение значений элементов по индекусу. 
 *
 */
public class Task07
{
    public static void main(String[] args)
    {
        String [] strings = new String[3];
        System.out.println(Arrays.toString(strings));
        strings[0] = "Васька"; // запись значения первого элемента
        strings[0] = "Мурзик"; // перезапись значения первого элемента
        strings[1] = "Сонька"; // запись значения второго элемента
        strings[2] = "Обормот"; // запись значения третьего элемента
        //System.out.println(Arrays.toString(strings)); // раскоментируй код
        
        
        char c = strings[2].charAt(0);
        System.out.println(c);
    }
}
