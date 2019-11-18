package ua.step.example.part3.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Сортировка выбором 
 */
public class Task06 {
    public static void main(String[] args)
    {
        Random rnd = new Random();
        int n = 10;
        int[] mas = new int[n];
        for (int i = 0; i < n; i++)
        {
            mas[i] = rnd.nextInt(n);
        }

        System.out.println(Arrays.toString(mas));
        // FIXME написать алгоритм сортировки выбором для массива и
        // подситать время портаченную на сортировку. Не изменять код сверху.
    }
}
