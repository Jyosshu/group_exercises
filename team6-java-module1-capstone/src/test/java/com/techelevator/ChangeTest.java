package com.techelevator;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.techelevator.Change;

public class ChangeTest {

	Change sut;
	
	@Before
	public void setUp() throws Exception {
		sut = new Change();
	}

	@Test
	public void testQuartersOnly() {
		sut.getChange(new BigDecimal("2.00"));
		assertEquals(8, sut.getQuarters());
		
	}

	@Test
	public void testDimes() {
		sut.getChange(new BigDecimal(".20"));
		assertEquals(2, sut.getDimes());
	}
	
	@Test 
	public void testQuartersNickels() {
		sut.getChange(new BigDecimal("1.15"));
		
		
		
		assertEquals(4, sut.getQuarters());
		assertEquals(1, sut.getDimes());
		assertEquals(1, sut.getNickels());
		
	}
}
