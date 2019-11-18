package ua.step.example.part2.mstatic;
/**
 * 
 * Форматирование данных в строке
 *
 */
public class Task03
{
    public static void main(String[] args)
    {
        for (int i = 0; i< 10; i++)
        {
        	for (int j = 0; j < 10; j++) {
            	System.out.printf("%d", i * j + i + j); // выделение места под число				
			}
        	System.out.printf("%n"); // новая строка
	    }
    }
}
// FIXME исправь пример чтобы в консоле было видно строки и столбцы