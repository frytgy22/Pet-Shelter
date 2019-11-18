package ua.step.homework02;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ua.step.BaseTest;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class TaskTest06 extends BaseTest {
    @Parameterized.Parameters(name = "string = {0} result = {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Hello World", "Слов - 2\n" +
                        "Гласных - 3\n" +
                        "Согласных - 7"},
                {"I Love Java", "Слов - 3\n" +
                        "Гласных - 5\n" +
                        "Согласных - 4"},
                {"I Like ITStep", "Слов - 3\n" +
                        "Гласных - 5\n" +
                        "Согласных - 6"},
                {"OpenJDK", "Слов - 1\n" +
                        "Гласных - 2\n" +
                        "Согласных - 5"},
        });
    }

    private final String input;
    private final String expected;

    public TaskTest06(String input, String expected) {
        super(null, Task06.class);
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
