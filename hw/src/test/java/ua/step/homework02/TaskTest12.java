package ua.step.homework02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ua.step.BaseTest;
import ua.step.homework01.Task09;
import ua.step.homework02.Task12;


@RunWith(Parameterized.class)
public class TaskTest12 extends BaseTest {

	private final String expected;

	@Parameterized.Parameters(name = "seed = {0}, size = {1}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
				{"0", "1 * 9 = 9\n" +
						"10 * 8 = 80\n" +
						"6 * 4 = 24\n" +
						"2 * 2 = 4\n" +
						"10 * 5 = 50\n" +
						"8 * 8 = 64\n" +
						"4 * 3 = 12\n" +
						"6 * 5 = 30\n" +
						"2 * 1 = 2\n" +
						"4 * 9 = 36\n" +
						"5 * 8 = 40\n" +
						"3 * 1 = 3\n" +
						"6 * 6 = 36\n" +
						"3 * 3 = 9\n" +
						"9 * 6 = 54"},

		});
	}

	public TaskTest12(String seed, String expected) {
		super(new String[]{seed}, Task12.class);
		this.expected = expected;
	}

	@Test
	public void test() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		super.invokeMain(this.args);
		String errorMessage = "Ожидается " + expected;
		String actual = outContent.toString().replaceAll("\\r", "");
		assertEquals(errorMessage, expected, actual.trim());
	}
}