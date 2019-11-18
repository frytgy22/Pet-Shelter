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
import ua.step.homework01.Task03;
import ua.step.homework02.Task01;

@RunWith(Parameterized.class)
public class TaskTest01 extends BaseTest {

	@Parameterized.Parameters(name = "word = {0} result = {1}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
				{"обороноспособность\nо","0 2 4 6 9 11 14 \n7"},
				{"aaa aa\na","0 1 2 4 5 \n5"},
				{"aaa aa\nо","-1"},
		});
	}

	private final String input;
	private final String expected;

	public TaskTest01(String input, String expected) {
		super(null, Task01.class);
		this.input = input;
		this.expected = expected;
	}

	@Test
	public void test() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if(testClass == null) return;
		String errorMessage = String.format("Ожидается, что для строки %s результат будет %s", input, expected);
		super.systemInputTest(input, expected, errorMessage);
	}
}
