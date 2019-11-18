package ua.step.homework02;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ua.step.homework02.Task11;

public class TaskTest11 {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));

	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

	@Test
	public void test1() {
		String inputData = "Ты дурак или чудак";
		System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));
		Task11.main(null);
		String errorMessage = "Wrong result";
		assertTrue(errorMessage, outContent.toString().contains("Ты *** или чудак"));
	}

	@Test
	public void test2() {
		String inputData = "Ты дурак или дурак";
		System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));
		Task11.main(null);
		String errorMessage = "Wrong result";
		assertTrue(errorMessage, outContent.toString().contains("Ты *** или ***"));
	}

	@Test
	public void test3() {
		String inputData = "Ты дУрАк или чудак";
		System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));
		Task11.main(null);
		String errorMessage = "Wrong result";
		assertTrue(errorMessage, outContent.toString().contains("Ты *** или чудак"));
	}

	@Test
	public void test4() {
		String inputData = "Ты д_У__рАк или чудак";
		System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));
		Task11.main(null);
		String errorMessage = "Wrong result";
		assertTrue(errorMessage, outContent.toString().contains("Ты *** или чудак"));
	}

	@Test
	public void test5() {
		String inputData = "Ты дураk или чудак";
		System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));
		Task11.main(null);
		String errorMessage = "Wrong result";
		assertTrue(errorMessage, outContent.toString().contains("Ты *** или чудак"));
	}

	@Test
	public void test6() {
		String inputData = "Ты ду-pa_k или чудак";
		System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));
		Task11.main(null);
		String errorMessage = "Wrong result";
		assertTrue(errorMessage, outContent.toString().contains("Ты *** или чудак"));
	}

	@Test
	public void test7() {
		String inputData = "Ты дур@к или чудак";
		System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));
		Task11.main(null);
		String errorMessage = "Wrong result";
		assertTrue(errorMessage, outContent.toString().contains("Ты *** или чудак"));
	}
}
