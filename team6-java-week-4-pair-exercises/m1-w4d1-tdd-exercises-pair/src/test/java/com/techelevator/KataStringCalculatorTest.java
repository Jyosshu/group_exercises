package com.techelevator;

import org.junit.*;
import static org.junit.Assert.*;
	
public class KataStringCalculatorTest {
	KataStringCalculator sut;
	@Before
	public void setup () {
		sut = new KataStringCalculator();
	}
	
	@Test
	public void testForEmpty() {
		int result = sut.add("");
		sut.add("");
		assertEquals(0, result);
	}
	@Test
	public void testOne() {
		int result = sut.add("1");
		assertEquals(1, result);
	}
	@Test
	public void testAdd2() {
		int result = sut.add("2,2");
		assertEquals(4, result);
	}
	@Test
	public void testAdd3() {
		int result = sut.add("2,2,2");
		assertEquals(6, result);
	}
	@Test
	public void testAddDoubleDigits() {
		int result = sut.add("20,20");
		assertEquals(40, result);
	}
	@Test
	public void testCommaNewLine() {
		int result = sut.add("1\n1");
		assertEquals(2, result);
	}
	@Test
	public void testDelimiterChange() {
		int result = sut.add("//;\n1;2");
		assertEquals(3, result);
	}
	
}

