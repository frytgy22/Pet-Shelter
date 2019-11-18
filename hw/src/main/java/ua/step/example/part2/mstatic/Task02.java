package ua.step.example.part2.mstatic;
/**
 * 
 * Форматирование данных в строке
 *
 */
public class Task02
{
    public static void main(String[] args)
    {
        int feet = 4;
        short tail = 1;
        String name = "Васька";
        String template = "У кота по имени %s %d лапы и %d хвост";
        String cat = String.format(template, name, feet, tail);
        System.out.println(cat);
        
        cat = String.format(template, "Чернобль", 8, 3);
        System.out.println(cat);  

        float a = 1 / 3333f;
        // System.out.println(a);
        String sa = String.format("%f", a); 
        System.out.println(sa);
        sa = String.format("%.8f", a);
        System.out.println(sa);
        
        System.out.printf("%03d", 1); // дополнение чила нулями слева 
        
        System.out.printf("\n%3d %2d", 1, 11); // выделение места под число
    }
}
