package com.techelevator;

public class VolunteerWorker extends Payroll implements Worker {

	public VolunteerWorker() {
	}
	
	public VolunteerWorker(String firstName, String lastName) {
		setFirstName(firstName);
		setLastName(lastName);
		
	}
	
	@Override
	public double calculateWeeklyPay(int hoursWorked) {
		
		return hoursWorked * 0;
	}

}
