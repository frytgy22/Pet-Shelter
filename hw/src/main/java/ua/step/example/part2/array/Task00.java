package ua.step.example.part2.array;

import java.util.Arrays;

/**
 * 
 * Многомерные массивы
 * 
 */
public class Task00
{
    @SuppressWarnings("unused")
	public static void main(String[] args)
    {
        // объявление и инициализация двухмерного массива
    	int[][] mas = { { 1, 2 }, { 2, 1 } };
        System.out.println(Arrays.toString(mas));
        // FIXME закоментируй 17 и раскоментрируй 19 строку кода
        //System.out.println(Arrays.deepToString(mas));
        
    	// объявление трехмерного массива 
    	int [][][] mas1 = new int[2][3][2];
        //System.out.println(Arrays.deepToString(mas1));

        String[][] arr = new String[4][3];
        // запись значений в элемент двухмерного массива
        arr[0][0] = "1"; 
        arr[0][1] = "Васька"; 
        arr[0][2] = "121987102";
        //System.out.println(Arrays.deepToString(arr));
    }
}
