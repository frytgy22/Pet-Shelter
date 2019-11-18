package ua.step.example.part2.array;

import java.util.Arrays;

/**
 * 
 * Ступенчатые массивы и отложенная инициализация
 *
 */
public class Task07
{
    public static void main(String[] args)
    {
        int[][] mas = new int[3][];
        mas[0][0] = 1; // ошибка при выполнении (внутрение массивы еще не созданы)

        for (int i = 0; i < mas.length; i++)
        {
            mas[i] = new int[i + 1];
            for (int j = 0; j < i + 1; j++)
            {
                mas[i][j] = j + 1;
            }
        }
        System.out.println(Arrays.deepToString(mas));
        
    }
}
