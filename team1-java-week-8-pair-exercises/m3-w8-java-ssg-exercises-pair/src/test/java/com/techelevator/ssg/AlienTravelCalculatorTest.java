package com.techelevator.ssg;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.techelevator.ssg.model.AlienTravelCalculator;

public class AlienTravelCalculatorTest {

	AlienTravelCalculator sut;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		sut.setAge(25);
		sut.setModeTrans("Walking");
		sut.setPlanet("Uranus");
	}

	@Test
	public void theTest() {
		System.out.println(sut.getAge());
		System.out.println(sut.getModeTrans());
		System.out.println(sut);
		assertEquals(64408.77, sut.getYearsToPlanet());
	}

}
