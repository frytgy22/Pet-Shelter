package ua.step.homework02;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ua.step.BaseTest;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TaskTest07 extends BaseTest {
    @Parameterized.Parameters(name = "string = {0} result = {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Mama mIA", "mAMA Mia"},
                {"aBaB CddCddC", "AbAb cDDcDDc"},
                {"Java Is The Best Program Language In The World", "java is the best program language in the world"},
        });
    }

    private final String input;
    private final String expected;

    public TaskTest07(String input, String expected) {
        super(null, Task07.class);
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