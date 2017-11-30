package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankAccountTest {

	BankAccount sut;
	DollarAmount newDollar;
	
	@Before
	public void setUp() throws Exception {
		sut = new BankAccount();
		newDollar = new DollarAmount(1000);
	}

	@Test
	public void testSetBalance() {
		sut.setAccountNumber("00001");
		sut.deposit(newDollar);
		
	assertEquals("00001", sut.getAccountNumber());
	assertEquals( new DollarAmount(1000), sut.getBalance());
	}
	
	@Test
	public void testWithdraw() {
		sut.setAccountNumber("00001");
		sut.deposit(newDollar);
		
		sut.withdraw(new DollarAmount(500));
		
	assertEquals( new DollarAmount(500), sut.getBalance());
	}

	@Test
	public void testWithdrawTooMuch() {
		sut.setAccountNumber("00001");
		sut.deposit(newDollar);
		
		sut.withdraw(new DollarAmount(1500));
		
	assertEquals( new DollarAmount(-500), sut.getBalance());
	}

	@Test
	public void testTransfer() {
		sut.setAccountNumber("00001");
		sut.deposit(newDollar);
		
		BankAccount otherAccount = new BankAccount();
		sut.transfer(otherAccount, new DollarAmount(500));
		
	assertEquals( new DollarAmount(500), sut.getBalance());
	assertEquals(new DollarAmount(500), otherAccount.getBalance());
	}

			
	
}
