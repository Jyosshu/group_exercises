package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public class BankCustomer extends BankAccount {
	private String name;
	private String address;
	private String phoneNumber;
	private List<BankAccount> accounts = new ArrayList<>();
	
	public BankCustomer(String name, String address, String phoneNumber) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;

	}
	
	public void addAccount(BankAccount newAccount) {
		this.accounts.add(newAccount);
//<<<<<<< HEAD
//		
//	}
//	
//	public boolean isVip() {
//		
//		DollarAmount sum = new DollarAmount(0);
//		
//		for( BankAccount temp : accounts) {
//			sum = sum.plus(temp.getBalance());
//			if( sum.isGreaterThanOrEqualTo(new DollarAmount(2500000))) {
//				return true;
//		} 
//		}	
//		return false; 
//}
//=======
	}
	
	public boolean isVip() {
		DollarAmount sum = new DollarAmount(0);
		
		for (BankAccount temp : accounts) {
			sum = sum.plus(temp.getBalance());
		}
		if (sum.isGreaterThanOrEqualTo(new DollarAmount(2500000))){
			return true;
		}
		else {
			return false;
		}
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	

	public BankAccount[] getAccounts() {
		return accounts.toArray(new BankAccount[accounts.size()]);
	}

}
