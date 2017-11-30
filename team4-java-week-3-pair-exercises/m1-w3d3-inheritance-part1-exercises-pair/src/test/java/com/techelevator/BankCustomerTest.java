package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



public class BankCustomerTest {
	
	BankCustomer sut;

	@Before
	public void setUp() throws Exception {
		sut = new BankCustomer("John Doe", "1234 Mockingbird Lane", "1234567890");
	}

	@Test
	public void testConstructorName() {
		
		assertEquals("John Doe", sut.getName());
		assertEquals("1234 Mockingbird Lane", sut.getAddress());
		assertEquals("1234567890", sut.getPhoneNumber());
	}
	
	@Test
	public void testAddCheckAccount() {
		
		CheckingAccount checkAccount = new CheckingAccount();
		checkAccount.deposit(new DollarAmount(2000));
		
		sut.addAccount(checkAccount);
		
		assertEquals(new DollarAmount(2000), checkAccount.getBalance());
	}
		
	@Test
	public void testAddSavAccount() {
		
		SavingsAccount savAccount = new SavingsAccount();
		savAccount.deposit(new DollarAmount(12000));
		
		sut.addAccount(savAccount);
		
		for( BankAccount temp : sut.getAccounts())
			System.out.println(temp.getBalance());
		
		assertEquals(new DollarAmount(12000), savAccount.getBalance());
		
	}
	
	@Test
	public void testIsVip() {
		
		CheckingAccount checkAccount = new CheckingAccount();
		checkAccount.deposit(new DollarAmount(2000));
		
		sut.addAccount(checkAccount);
		
		assertEquals(new DollarAmount(2000), checkAccount.getBalance());
		
		SavingsAccount savAccount = new SavingsAccount();
		savAccount.deposit(new DollarAmount(12000));
		
		sut.addAccount(savAccount);
		
		for( BankAccount temp : sut.getAccounts())
			System.out.println(temp.getBalance());
		
		assertEquals(false, sut.isVip());
	}
	
	@Test
	public void testIsVipTrue() {
		
		CheckingAccount checkAccount = new CheckingAccount();
		checkAccount.deposit(new DollarAmount(3000000));
		sut.addAccount(checkAccount);
		
		SavingsAccount savAccount = new SavingsAccount();
		savAccount.deposit(new DollarAmount(0));
		sut.addAccount(savAccount);
		
		for( BankAccount temp : sut.getAccounts()) 
			System.out.println(temp.getBalance());
		
		assertEquals(true, sut.isVip());
}

//public class BankCustomerTest {
//
//	BankCustomer sut;
//	
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@Test
//	public void testConstructor() {
//		sut = new BankCustomer("John Doe", "1234 Mockingbird Lane", "1115555555");
//		
//		assertEquals("John Doe", sut.getName());
//		assertEquals("1234 Mockingbird Lane", sut.getAddress());
//		assertEquals("1115555555", sut.getPhoneNumber());
//	}
//	
//	@Test
//	public void testAddBankAccount() {
//		sut = new BankCustomer("John Doe", "1234 Mockingbird Lane", "1115555555");
//		
//		CheckingAccount sutBankAcc = new CheckingAccount();
//		sutBankAcc.deposit(new DollarAmount(1000));
//		
//		sut.addAccount(sutBankAcc);
//		
//		SavingsAccount sutSavAcc = new SavingsAccount();
//		sutSavAcc.deposit(new DollarAmount(50000));
//	
//		sut.addAccount(sutSavAcc);
//	
//		
//		//System.out.println(sutSavAcc.getBalance());
//		for (BankAccount temp : sut.getAccounts()) {
//			System.out.println(temp.getBalance());
//		}
//
//		
//		assertEquals(new DollarAmount(50000), sutSavAcc.getBalance());
//	}
//
//	@Test
//	public void testVipFalse() {
//		sut = new BankCustomer("John Doe", "1234 Mockingbird Lane", "1115555555");
//		
//		CheckingAccount sutBankAcc = new CheckingAccount();
//		sutBankAcc.deposit(new DollarAmount(1000));
//		
//		sut.addAccount(sutBankAcc);
//		
//		SavingsAccount sutSavAcc = new SavingsAccount();
//		sutSavAcc.deposit(new DollarAmount(50000));
//	
//		sut.addAccount(sutSavAcc);
//	
//		
//		//System.out.println(sutSavAcc.getBalance());
//		for (BankAccount temp : sut.getAccounts()) {
//			System.out.println(temp.getBalance());
//		}
//
//		
//		assertEquals(false, sut.isVip());
//	}
//	
//	@Test
//	public void testVipAlmostTrue() {
//		sut = new BankCustomer("John Doe", "1234 Mockingbird Lane", "1115555555");
//		
//		CheckingAccount sutBankAcc = new CheckingAccount();
//		sutBankAcc.deposit(new DollarAmount(499999));
//		
//		sut.addAccount(sutBankAcc);
//		
//		SavingsAccount sutSavAcc = new SavingsAccount();
//		sutSavAcc.deposit(new DollarAmount(2000000));
//	
//		sut.addAccount(sutSavAcc);
//	
//		
//		//System.out.println(sutSavAcc.getBalance());
//		for (BankAccount temp : sut.getAccounts()) {
//			System.out.println(temp.getBalance());
//		}
//
//		
//		assertEquals(false, sut.isVip());
//	}
//		
//	@Test
//	public void testVipBarelyTrue() {
//		sut = new BankCustomer("John Doe", "1234 Mockingbird Lane", "1115555555");
//		
//		CheckingAccount sutBankAcc = new CheckingAccount();
//		sutBankAcc.deposit(new DollarAmount(500001));
//		
//		sut.addAccount(sutBankAcc);
//		
//		SavingsAccount sutSavAcc = new SavingsAccount();
//		sutSavAcc.deposit(new DollarAmount(2000000));
//	
//		sut.addAccount(sutSavAcc);
//	
//		
//		//System.out.println(sutSavAcc.getBalance());
//		for (BankAccount temp : sut.getAccounts()) {
//			System.out.println(temp.getBalance());
//		}
//
//		
//		assertEquals(true, sut.isVip());
//	}
//	
//	@Test
//	public void testVipReallyTrue() {
//		sut = new BankCustomer("John Doe", "1234 Mockingbird Lane", "1115555555");
//		
//		CheckingAccount sutBankAcc = new CheckingAccount();
//		sutBankAcc.deposit(new DollarAmount(50000000));
//		
//		sut.addAccount(sutBankAcc);
//		
//		SavingsAccount sutSavAcc = new SavingsAccount();
//		sutSavAcc.deposit(new DollarAmount(2000000));
//	
//		sut.addAccount(sutSavAcc);
//	
//		
//		//System.out.println(sutSavAcc.getBalance());
//		for (BankAccount temp : sut.getAccounts()) {
//			System.out.println(temp.getBalance());
//		}
//
//		
//		assertEquals(true, sut.isVip());
//	}
//>>>>>>> 7721fde259b70a4a64d4fdb18d3958a9e8408ff7
}
