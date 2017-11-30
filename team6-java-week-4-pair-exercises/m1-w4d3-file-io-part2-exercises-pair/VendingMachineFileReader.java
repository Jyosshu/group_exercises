package com.techelevator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineFileReader {
	File inventoryList = new File("vendingmachine.csv");
	
	Map<String, Product>  products = new HashMap<String, Product>();
	
	try(Scanner inventoryInput = new Scanner(inventoryList)) {
		String line = null;
		String[] parts;
		
		while (inventoryInput.hasNextLine()) {
			line = inventoryInput.nextLine();
			parts = line.split("|");
			
			for (int i = 0; i < parts.length; i++) {
				products.setKey(parts[0]);
				
			}
		}
	}
	
	
}
