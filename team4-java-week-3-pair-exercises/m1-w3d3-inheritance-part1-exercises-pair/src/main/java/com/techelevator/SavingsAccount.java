package com.techelevator;

public class SavingsAccount extends BankAccount{

	@Override
	public DollarAmount withdraw(DollarAmount amountToWithdraw) {
		if (getBalance().minus(amountToWithdraw).isLessThan(new DollarAmount(0))) {
			return getBalance();
		}
		else if (getBalance().minus(amountToWithdraw).isLessThan(new DollarAmount(15000))) {
			super.withdraw(amountToWithdraw);
			return super.withdraw(new DollarAmount(200));
		}
		else {
			return super.withdraw(amountToWithdraw);
		}
		
	}
	

}
