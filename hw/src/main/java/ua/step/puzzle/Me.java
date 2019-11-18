package ua.step.puzzle;
/**
 * Разберитесь почему программа выводит не то, что ожидается 
 */
public class Me {
   public static void main(String[] args) {
      System.out.println(Me.class.getName().replaceAll(".", "/") + ".class");
   }
}
