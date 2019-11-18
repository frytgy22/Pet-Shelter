package ua.step.example.part1.string;

/**
 * Длина строки 
 *
 */
public class Task05
{
    public static void main(String[] args)
    {
        String testString = "kitten"; 
        int lenght = testString.length();
        System.out.println(lenght);
        testString = "cat";
        lenght = testString.length();
        System.out.println(lenght);
        
        for(int i = 0 ; i<1000; i++)
        {
        	testString+=i;
        }
        System.out.println(testString.length());
    }
}