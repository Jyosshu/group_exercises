package com.techelevator;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class VendingMachine {

	private Map<String, Stack<Product>> inventory = null; // Map passed from CLI
	private List<Product> purchases = new ArrayList<>(); // List to hold customer purchases
	private BigDecimal currentBalance = new BigDecimal("0.00");

	private WriteFile data = new WriteFile("log.txt", true);

	public VendingMachine(Map<String, Stack<Product>> inventory) { // Constructor
		this.inventory = inventory;
	}
	
	public List<Product> getPurchases() {
		return purchases;
	}
	
	@Override
	public String toString() {
		String display = null;
		
		for (Map.Entry<String, Stack<Product>> entry : inventory.entrySet()) {
			display = entry.getKey() + " " + entry.getValue() + "\n";
		}
		return display;
	}
	
	public BigDecimal getBalance() {
		
		return currentBalance;
	}
	
	public void addMoney(BigDecimal dollars) throws IOException { // change return ??
		currentBalance = currentBalance.add(dollars);
					 
		
		data.writeToFile(String.format("%-25s %-8s %s", "ADD MONEY", "$"+dollars+".00", "$"+currentBalance));
//		data.writeToFile("ADD MONEY " + "$" + dollars + ".00 " + " $" + currentBalance);
		

	}
	
	public BigDecimal getProductPrice(String slot) {
		BigDecimal itemPrice = null;
		if(getProductQuant(slot) != 0){
			itemPrice = this.inventory.get(slot).peek().getPrice();
		}
		return itemPrice;
	}
	
	public String getProductName(String slot) {
		String productName =  null;
		productName = this.inventory.get(slot).peek().getName();
		return productName;
	}
	
	public int getProductQuant(String slot) {
		int productQuant = this.inventory.get(slot).size();
		return productQuant;
	}
	
	public void purchase(String slot) throws VendingMachineException {
		
		if (inventory.containsKey(slot)) {
			if (currentBalance.compareTo(getProductPrice(slot)) >= 0 ) {
				if (inventory.get(slot).size() > 0) {
					BigDecimal temp = currentBalance;
					currentBalance = currentBalance.subtract(getProductPrice(slot));
					String productName = getProductName(slot);
					purchases.add(this.inventory.get(slot).pop());
					
					try{		
						data.writeToFile(String.format("%-18s %-6s %-8s %s", productName, slot, "$"+temp, "$"+currentBalance));
					}
					catch (IOException e) { 
						System.out.println(e.getMessage());
					}
					
				}
				else {
					// Add a comment to this line
					throw new VendingMachineException("This item is sold out");
				}

			}
			else {
				throw new VendingMachineException("Insufficient Funds.");
			}
		}		 		
			else {
				throw new VendingMachineException("Invalid item selected");
			}
					//Add a comment to this line		 	

	}	

	public void finishTransaction() {
		BigDecimal beforeFinal = currentBalance;
		// return customer's money  change()
		if (currentBalance.compareTo(new BigDecimal("0")) != 0) {
			Change custChange = new Change();
			
			custChange.getChange(currentBalance);
		
			System.out.println("\n" + custChange);	
		}
		// 	currentBalance updated to $0
			currentBalance = new BigDecimal("0");
		try{	
			data.writeToFile(String.format("%10s %20s %7s", "GIVE CHANGE", "$"+beforeFinal, "$"+currentBalance+".00"));
		}
	catch (IOException e) { 
			System.out.println(e.getMessage());
		}
		
		// the purchases will be "consumed"
		for (Product bought : purchases) {
			System.out.println(bought.getSound());
		}
		purchases.clear();
		System.out.println("Thank you for your business!");
		//System.exit(1);
	}
	
}

