package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public class KataStringCalculator {
	private List<Integer> numToAdd = new ArrayList<>();
	private int result = 0;
	private String delimiter = ",|\n|;";
	
	public int add(String numbers) {
		String input = numbers;  // .replaceAll("\\s", "")
		if (input.length() > 2 && input.substring(0, 2).equals("//")) {
			delimiter = input.substring(2,3);
			input = input.replace("//", "");
			input = input.replace("\n", "");
		}
		System.out.println(delimiter);
		String[] stringHolder = input.split(delimiter);
		
		if (stringHolder.length <= 1 && stringHolder[0].equals("")) {
			return result = 0;
		} 
		else {
			for (int i = 0; i < stringHolder.length; i++) {
				if (stringHolder[i].equals("")) {
				
				} else {
				numToAdd.add(Integer.parseInt(stringHolder[i]));
				} 
			}
		 
			
			for (Integer element : numToAdd) {
				result += element;
			
			} return result;
		}
	} 
}
