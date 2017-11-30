package com.techelevator;

import java.math.BigDecimal;

public class GumItem extends Product {

	public GumItem(String name, BigDecimal price) {
		super(name, price);
	}
	
	@Override
	public String getSound() {
 
		return "Chew Chew, Yum!";
	}

}
