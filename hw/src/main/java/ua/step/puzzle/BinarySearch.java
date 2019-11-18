package ua.step.puzzle;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BinarySearch
{
    public static void main(String[] args)
    {
        Random rnd = new Random();
        int n = 150;
        int[] mas = new int[n];
        for (int i = 0; i < n; i++)
        {
            mas[i] = rnd.nextInt(n);
        }
        Arrays.sort(mas);
        System.out.println(Arrays.toString(mas));
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите искомое число:");
        int key = scanner.nextInt();
        int left = 0;
        int right = mas.length - 1;
        
        int mid = -1;
        boolean descendingOrder = mas[left] > mas[right];
        while (left < right)
        {
            mid = (left + right) >>> 1;
            int value = mas[mid];

            if ((value > key) ^ descendingOrder)
            {
                right = mid;
            }
            else if (value != key)
            {
                left = mid + 1;
            }
            if (value == key)
            {
                if (mas[left] == key)
                {
                    mid = left;
                    break;
                }
                else
                {
                    left++;
                    right = mid + 1;
                }
            }
        }
        System.out.println(mid);
        scanner.close();
    }
}
