package ua.step.example.part1.string;

/**
 * 
 * Сложение строк и литералов приметивных типов
 *
 */
public class Task04
{
    public static void main(String[] args)
    {
        int digit = 4; 
        String paws = " лап ";

        String aboutCat =  digit + 2 + paws ;  
        System.out.println(aboutCat);

        // FIXME добавьте в выражение скобки (2 + 2) и посмотриет как изменится результат
        aboutCat = paws + 2 + 2; 
        System.out.println(aboutCat);
        
        aboutCat = paws + 2 + 2 * 2 ;
        //System.out.println(aboutCat);
        
        System.out.println("Истина дороже  " + true);
        System.out.println("Дробное " + 1.2);
    }
}