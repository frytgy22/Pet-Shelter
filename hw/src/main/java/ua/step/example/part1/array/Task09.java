package ua.step.example.part1.array;

/**
 * 
 * Ошибка  выхода за пределы массива и ошибка NullPointerException
 *
 */
public class Task09
{
    public static void main(String[] args)
    {
        String [] cats = new String[]{"Васька", "Мурка", "Боня",};
        String s = cats[3]; // индекс 3 - это четвертый элемент, ошибка так как элементов всего 3
        cats = new String[3]; // создание нового массива, в котором все элементы равны null
        cats[0] = s;
        String s1 = cats[1]; // элемент еще не присваивался поэтому будет возвращен null
        System.out.println(s1.charAt(0));
        //FIXME исправь пример, чтобы выводилась последняя буква третьего слова.
    }
}