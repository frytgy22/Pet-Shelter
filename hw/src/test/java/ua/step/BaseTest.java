package ua.step;

import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public abstract class BaseTest {
    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    protected final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    protected Class testClass;
    protected final String[] args;

    protected BaseTest() {
        this(null, null);
    }

    protected BaseTest(String[] args, Class testClass) {
        this.args = args;
        this.testClass = testClass;
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
        System.setIn(null);
    }

    protected void systemInputTest(String input, String expected, String errorMessage) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        invokeMain(this.args);
        String out = outContent.toString().trim().replaceAll("\\r", "").toLowerCase();
        int idx = out.lastIndexOf(":"); // Разделитель ввода пользователя
        String actual = out.substring(idx + 2);
        assertEquals(errorMessage, expected.toLowerCase(), actual);
    }

    protected void invokeMain(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method main = testClass.getMethod("main", String[].class);
        main.invoke(null, (Object) args);
    }
}
