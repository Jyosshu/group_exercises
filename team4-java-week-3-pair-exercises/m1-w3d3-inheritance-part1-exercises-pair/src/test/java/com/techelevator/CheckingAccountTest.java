package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CheckingAccountTest {

	BankAccount sut;
	DollarAmount newDollar;
	
	@Before
	public void setUp() throws Exception {
		sut = new CheckingAccount();
		newDollar = new DollarAmount(1000);
	}

	@Test
	public void test() {
		sut.setAccountNumber("00001");
		sut.deposit(newDollar);
		
		sut.withdraw(new DollarAmount(500));
		
	assertEquals( new DollarAmount(500), sut.getBalance());
	}

	@Test
	public void testNegative10() {
		sut.setAccountNumber("00001");
		sut.deposit(newDollar);
		
		sut.withdraw(new DollarAmount(2000));
		
	assertEquals( new DollarAmount(-2000), sut.getBalance());
	}

	@Test
	public void testNegative100() {
		sut.setAccountNumber("00001");
		sut.deposit(newDollar);
		
		sut.withdraw(new DollarAmount(120000));
		
	assertEquals( new DollarAmount(1000), sut.getBalance());
	}

}
