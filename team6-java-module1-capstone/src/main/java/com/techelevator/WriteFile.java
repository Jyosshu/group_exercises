package com.techelevator;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Date;

public class WriteFile {
	
	private String path;
	private boolean appendToFile = false;
	
	
//	public WriteFile (String filePath) {
//		path = "vendingMachineLog.txt";
//	}
	
	public WriteFile(String filePath , boolean appendValue ) {
		path = filePath;
		appendToFile = appendValue;
		}
	
	@SuppressWarnings("deprecation")
	public void writeToFile(String textLine) throws IOException {
		
		FileWriter write = new FileWriter(path, appendToFile);
		
		PrintWriter printLine = new PrintWriter(write);
		
		Date today = new Date();
		
		printLine.printf("%s %s", today.toLocaleString(), textLine + "\n");
		
		printLine.close();
		
	}

	@Override
	public String toString() {
		return "WriteFile [path=" + path + ", appendToFile=" + appendToFile + "]";
	}
	
}
