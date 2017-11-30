package com.techelevator;

import java.math.BigDecimal;

public class ChipItem extends Product {

	public ChipItem(String name, BigDecimal price) {
		super(name, price);
	}
	
	@Override
	public String getSound() {

		return "Crunch, Crunch, Yum!";
	}

}
