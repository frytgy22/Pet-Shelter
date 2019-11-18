package ua.step.example.part1.string;

/**
 * 
 * Замена символов в строке
 *
 */
public class Task17
{
    @SuppressWarnings("unused")
	public static void main(String[] args)
    {
    	String cat = "кит";
        //FIXME сделайте из кита кота используюя replace
    	cat.replace('и', 'о'); 
        System.out.println(cat);
        
        // FIXME выведите строку в которой вместо x будет стоять символ 0 
        String test = "xyyyxxxxyyxyxyxyyyxyxyxy";
        
     }
}