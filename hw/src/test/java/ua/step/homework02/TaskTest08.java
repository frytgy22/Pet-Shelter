package ua.step.homework02;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ua.step.BaseTest;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TaskTest08 extends BaseTest {
    @Parameterized.Parameters(name = "string = {0} result = {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"java_home", "javaHome"},
                {"javaHome", "java_home"},
                {"_java_home", "javaHome"},
                {"j_a_v_a_h_o_m_e", "jAVAHOME"},
                {"mainJavaHome", "main_java_home"},
                {"main_java_home", "mainJavaHome"},
        });
    }

    private final String input;
    private final String expected;

    public TaskTest08(String input, String expected) {
        super(null, Task08.class);
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void test() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (testClass == null) return;
        String errorMessage = String.format("Ожидается, что для строки %s результат будет %s", input, expected);
        super.systemInputTest(input, expected, errorMessage);
    }
}
