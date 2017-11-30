package com.techelevator;

public class SalaryWorker extends Payroll implements Worker {

	private double annualSalary;
	
	public SalaryWorker(double annualSalary) {
		this.annualSalary = annualSalary;
	}
	public SalaryWorker(String firstName, String lastName, double annualSalary) {
		setFirstName(firstName);
		setLastName(lastName);
		this.annualSalary= annualSalary;
	
	}
	@Override
	public double calculateWeeklyPay(int hoursWorked) {
		return annualSalary / 52;
	}

	public double getAnnualSalary() {
		return annualSalary;
	}
	
}
