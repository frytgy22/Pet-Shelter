package ua.step.homework02;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ua.step.BaseTest;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TaskTest09 extends BaseTest {
    @Parameterized.Parameters(name = "string = {0} result = {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"«Аз есмь строка, живу я, мерой остр.»\n«За семь морей ростка я вижу рост!»", "Yes"},
                {"Мама мыла раму\nРыло надо мыть с мылом!", "No"},
                {"растение\nстарение", "Yes"},
                {"растение\nстарение", "Yes"},
        });
    }

    private final String input;
    private final String expected;

    public TaskTest09(String input, String expected) {
        super(null, Task09.class);
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
