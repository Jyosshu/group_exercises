package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FindAndReplace {    //alices_adventures_in_wonderland.txt  joe_ericksons_adventures_in_wonderland.txt

	public static void main(String[] args) throws FileNotFoundException {
		
		String searchWord = null;
		String replaceWord = null;
		String sourceFile = null;
		String destinationFile = null;
		
		File originalFile;  
		File temporaryFile;
	
		
		try(Scanner userInput = new Scanner(System.in)) {
		
			System.out.println("What word do you want to search for?");
			searchWord = userInput.nextLine();
		
			System.out.println("Which word would you like to replace " + searchWord + " with");
			replaceWord = userInput.nextLine();
		
			System.out.println("What is the source file location?");
			sourceFile = userInput.nextLine();
		
			System.out.println("What is the destination for your new masterpiece? ( * .txt )");
			destinationFile = userInput.nextLine();
		}
				
				originalFile  = new File(sourceFile);
				temporaryFile = new File(destinationFile);
						
		try(
				Scanner readPom = new Scanner(originalFile); 
				PrintWriter writePom = new PrintWriter(temporaryFile)  
		) {
			while(readPom.hasNextLine()) {
				String line = readPom.nextLine(); 
				if(line.toLowerCase().contains(searchWord.toLowerCase())) {  
					line = line.replaceAll("(?!\\W)" + searchWord + "(?=\\W)", replaceWord); 
				}
				writePom.println(line);  //writes lines from readPom
			}  //at the end of this try with resources block the buffer will flush and program will close.
		} catch (FileNotFoundException e) {
			
			System.out.println("The file doesn't exist");
			System.exit(1);
		}
		
	}	

}
