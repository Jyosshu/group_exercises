package com.techelevator;

public class HourlyWorker extends Payroll implements Worker {
	
	private double hourlyRate;

	public HourlyWorker(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	public HourlyWorker(String firstName, String lastName, double hourlyRate) {
		setFirstName(firstName);
		setLastName(lastName);
		this.hourlyRate = hourlyRate;
	}
	
	@Override
	public double calculateWeeklyPay(int hoursWorked) {
		double pay = hourlyRate * hoursWorked;
		double overtime = hoursWorked - 40;
		pay = pay + (hourlyRate * overtime * .5);
		return pay;
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

}
