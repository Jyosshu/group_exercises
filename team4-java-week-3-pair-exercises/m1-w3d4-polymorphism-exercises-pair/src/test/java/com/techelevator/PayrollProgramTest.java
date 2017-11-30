package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PayrollProgramTest {
	
	Worker sut;

	@Before
	public void setUp() throws Exception {
		
		
	}

	@Test
	public void testSalaryFirstName() {
		
		SalaryWorker one = new SalaryWorker("Mickey", "Mouse", 45000);
		assertEquals("Mickey", one.getFirstName());
	}
	
	@Test
	public void testSalarySalary() {
		
		SalaryWorker one = new SalaryWorker("Mickey", "Mouse", 45000);
		assertEquals(45000 / 52, one.calculateWeeklyPay(0), 1);
	}
	
	@Test
	public void testConstructors() {
		
		SalaryWorker one = new SalaryWorker("Mickey", "Mouse", 45000);
		assertEquals(45000, one.getAnnualSalary(), 1);
		assertEquals("Mickey", one.getFirstName());
		assertEquals("Mouse", one.getLastName());
	}
	@Test
	public void testVolunteerWorker()  {
		VolunteerWorker two = new VolunteerWorker("Daisy", "Duck");
		assertEquals("Daisy", two.getFirstName());
		assertEquals("Duck", two.getLastName());
	}
	@Test
	public void testHourlyConstructors() {
		HourlyWorker three = new HourlyWorker("Goofy", "Geef", 9.50);
		assertEquals(9.50, three.getHourlyRate(), 1);
		assertEquals("Goofy", three.getFirstName());
		assertEquals("Geef", three.getLastName());
	}
	@Test
	public void testHourlyWeekPay() {
		
		HourlyWorker four = new HourlyWorker("Goofy", "Geef", 9.50);
		assertEquals(380, four.calculateWeeklyPay(40), 1);
	}
}
