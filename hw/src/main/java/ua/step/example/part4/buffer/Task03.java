package ua.step.example.part4.buffer;
/**
 * Добавление объектов в строчный буфер 
 */
public class Task03
{
    public static void main(String[] args)
    {
        StringBuffer sb = new StringBuffer();
        for (char i = 'A', j = 'a'; i <= 'Z'; i++, j++) {
			sb.append(i);
			sb.append(j);
		}
        System.out.println(sb);
        //FIXME измени пример, так чтобы в строке буквы были разделены пробелами  на пары
    }
}
