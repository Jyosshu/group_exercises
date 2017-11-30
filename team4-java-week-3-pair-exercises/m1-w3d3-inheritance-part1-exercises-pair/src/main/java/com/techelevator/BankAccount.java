package com.techelevator;

public class BankAccount {

	private String accountNumber = "";
	private DollarAmount balance;
	
	public BankAccount() {
		balance = new DollarAmount(0);
	}
	
	public DollarAmount deposit(DollarAmount amountToDeposit) {
		return this.balance = balance.plus(amountToDeposit) ;
	}
	
	public DollarAmount withdraw(DollarAmount amountToWithdraw) {
		return this.balance = balance.minus(amountToWithdraw);	
	}
	
	public void transfer(BankAccount destinationAccount, DollarAmount transferAmount) {
		this.withdraw(transferAmount);
		destinationAccount.deposit(transferAmount);
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public DollarAmount getBalance() {
		return balance;
	}
	
	
	
}
