package com.techelevator;

import java.util.ArrayList;
import java.util.List;


public class PayrollProgram {

	public static void main(String[] args) {
		
		SalaryWorker mickey = new SalaryWorker("Mickey", "Mouse", 45000);
		HourlyWorker daisy = new HourlyWorker("Daisy", "Duck", 9.50);
		VolunteerWorker goofy = new VolunteerWorker("George", "Geef");
		
		List <Worker> weeklyPayroll = new ArrayList<>();
			weeklyPayroll.add(mickey);
			weeklyPayroll.add(daisy);
			weeklyPayroll.add(goofy);
			
			
			
			System.out.println(String.format("%-20s%-20s%-20s", "Employee", "Hours Worked", "Pay"));
			
			int totalHours = 0;
			double totalPay = 0.00;

			for( Worker pay : weeklyPayroll) {
			String name = pay.getLastName();
			name += ", " + pay.getFirstName();
			int hours = 0 + (int)(Math.random() * 100);
			System.out.println(String.format("%-20s%-20d%-1s%-20.2f",name , hours, "$", pay.calculateWeeklyPay(hours)));
			totalHours += hours;
			totalPay += pay.calculateWeeklyPay(hours);
		}
			System.out.println();
			System.out.println("Total Hours: " + totalHours );
			System.out.println(String.format("%-10s%-10.2f","Total Pay: $" ,totalPay));
	}
}