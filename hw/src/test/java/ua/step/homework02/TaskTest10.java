package ua.step.homework02;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ua.step.homework02.Task10;

public class TaskTest10 {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	private String test = "      t\r\n     te\r\n    tes\r\n   test\r\n  testi\r\n testin\r\ntesting\r\nesting\r\nsting\r\nting\r\ning\r\nng\r\ng\r\n";
	
	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));

	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

	@Test
	public void test() {
		String inputData = "testing";
		System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));
		Task10.main(null);
		String errorMessage = "Wrong result";
		assertTrue(errorMessage, outContent.toString().contains(test));
	}
}
