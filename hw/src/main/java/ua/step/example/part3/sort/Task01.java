package ua.step.example.part3.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 
 * Суммирование элементов массива
 *
 */
public class Task01
{
    public static void main(String[] args)
    {
        Random rnd = new Random();
        int n = 10;
        int[][] mas = new int[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                mas[i][j] = rnd.nextInt(n);
            }
        }
        System.out.println(Arrays.deepToString(mas));

        // FIXME расчитать сумму всех элементов массива и подсчитать время
        // потраченное на суммирование, не менять код выше.
        // Суммировать по строкам и столбцам
    }
}
