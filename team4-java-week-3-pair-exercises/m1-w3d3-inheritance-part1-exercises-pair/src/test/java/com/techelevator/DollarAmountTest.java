package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DollarAmountTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testOutputMiddle() {
	DollarAmount sut = new DollarAmount(3210);	
	
	assertEquals("$32.10", sut);
	}

}
