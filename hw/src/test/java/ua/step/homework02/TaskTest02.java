package ua.step.homework02;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ua.step.BaseTest;
import ua.step.homework02.Task02;

@RunWith(Parameterized.class)
public class TaskTest02 extends BaseTest {

	@Parameterized.Parameters(name = "position = {0} result = {1}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
				{"1","1"},
				{"2","2"},
				{"3","3"},
				{"9","9"},
				{"10","1"},
				{"11","0"},
				{"12","1"},
				{"13","1"},
				{"14","1"},
				{"100","5"},
				{"200","0"},
				{"300","6"},
				{"400","1"},
				{"500","0"},
				{"1000","3"},
		});
	}

	private final String input;
	private final String expected;

	public TaskTest02(String input, String expected) {
		super(null, Task02.class);
		this.input = input;
		this.expected = expected;
	}

	@Test
	public void test() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if(testClass == null) return;
		String errorMessage = String.format("Ожидается, что для позиции %s результат будет %s", input, expected);
		super.systemInputTest(input, expected, errorMessage);
	}
}
