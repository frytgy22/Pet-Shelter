package ua.step.homework02;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ua.step.BaseTest;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TaskTest04 extends BaseTest {
	@Parameterized.Parameters(name = "string = {0} result = {1}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
				{"ab baa aba abc","ab abc"},
				{"idi if oko egh upl","if egh upl"},
				{"yes no noupe yep yea","yes yep"},
		});
	}

	private final String input;
	private final String expected;

	public TaskTest04(String input, String expected) {
		super(null, Task04.class);
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