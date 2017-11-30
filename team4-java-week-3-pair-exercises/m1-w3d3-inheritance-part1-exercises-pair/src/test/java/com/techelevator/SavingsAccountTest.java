package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SavingsAccountTest {

	BankAccount sut;
	DollarAmount newDollar;
	
	@Before
	public void setUp() throws Exception {
		sut = new SavingsAccount();
		newDollar = new DollarAmount(20000);
	}

	@Test
	public void test() {
		sut.setAccountNumber("00001");
		sut.deposit(newDollar);
		
		sut.withdraw(new DollarAmount(500));
		
	assertEquals( new DollarAmount(19500), sut.getBalance());
	}

	@Test
	public void testUnder50() {
		sut.setAccountNumber("00001");
		sut.deposit(newDollar);
		
		sut.withdraw(new DollarAmount(7500));
		
	assertEquals( new DollarAmount(12300), sut.getBalance());
	}

	@Test
	public void testNegative100() {
		sut.setAccountNumber("00001");
		sut.deposit(newDollar);
		
		sut.withdraw(new DollarAmount(120000));
		
	assertEquals( new DollarAmount(20000), sut.getBalance());
	}


}
