package com.techelevator;

public class CheckingAccount extends BankAccount {

	@Override
	public DollarAmount withdraw(DollarAmount amountToWithdraw) {
		if (getBalance().minus(amountToWithdraw).isLessThanOrEqualTo(new DollarAmount(-10000))) {
			return getBalance();
		}
		
		super.withdraw(amountToWithdraw);
		
		if (getBalance().isLessThan(new DollarAmount(0))) {
			return super.withdraw(new DollarAmount(1000));
		}
		else {
			return getBalance();
		}
			
	}

	
}
