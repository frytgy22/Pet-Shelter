package ua.step.example.part1.string;

/**
 * 
 * Сравнение строк. Интернированиею 
 *
 */
public class Task11
{
    public static void main(String[] args)
    {
        String cat1 = "cat";
        String cat2 = "cat"; // ссылка на объект взята из пула строк
        System.out.println(cat1 == cat2); // сравнение ссылок
        System.out.println(cat1.equals(cat2)); // сравнение содержимого 
        System.out.println();
        		
        cat1 = new String("cat"); // новая ссылка
        cat2 = new String("cat"); // новая ссылка
        System.out.println("new cat " + (cat1 == cat2)); // сравнение ссылок
        System.out.println("new cat " + cat1.equals(cat2)); // сравнение содержимого 
        System.out.println();
        
        cat1 = cat1.intern(); // извлечение ссылки на строку из пула  
        cat2 = cat2.intern(); // извлечение ссылки на строку из пула
        System.out.println("internal cat " + (cat1 == cat2)); // обратите внимание на скобки
        System.out.println("internal cat " + cat1.equals(cat2));
        System.out.println();
        
        String t = "t";
        cat1 = "ca" + t;
        System.out.println("dynamic cat " + (cat1 == cat2));
        System.out.println("dynamic cat " +  cat1.equals(cat2));
    }
}
