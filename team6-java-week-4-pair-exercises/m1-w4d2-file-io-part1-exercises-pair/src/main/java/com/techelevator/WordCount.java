package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordCount {
	static int wordCount = 0;
	static int sentenceCount = 0;
	static String delimiters ="[!?.]+";
	
	public static void main(String[] args) {
		
		
		
		File alice = new File("/Users/aaronvail/workspace/team6-java-week-4-pair-exercises/m1-w4d2-file-io-part1-exercises-pair/alices_adventures_in_wonderland.txt");
//		System.out.println(alice.exists());
		
		try(Scanner input = new Scanner(alice)) {
			while (input.hasNextLine()) {
				
				input.next();
				wordCount++;
				
				
			}
		} catch (FileNotFoundException e) {
			System.out.println("Your file does not exist.");
			e.printStackTrace();
		}
		sentenceCount = getSentenceCount();
		
		System.out.println("Number of words : " + wordCount);
		System.out.println("The number of sentences is : " + sentenceCount);
		
	}

	public static int getSentenceCount() {
		
		File alice = new File("/Users/aaronvail/workspace/team6-java-week-4-pair-exercises/m1-w4d2-file-io-part1-exercises-pair/alices_adventures_in_wonderland.txt");
		// System.out.println(alice.exists());
		
		try(Scanner input = new Scanner(alice)) {
			while (input.hasNextLine()) {
			
				String line = input.nextLine();
				
				if (line.contains("!") || line.contains("?") || line.contains(".")) {
					sentenceCount++;
				}
				
			}
			
		
		} catch (FileNotFoundException e) {
		System.out.println("Your file does not exist.");
		e.printStackTrace();
		}
		
	return sentenceCount;
	}	
}
