package com.techelevator;

import java.math.BigDecimal;

public class CandyItem extends Product {

	public CandyItem(String name, BigDecimal price) {
		super(name, price);
	}
	
	@Override
	public String getSound() {

		return "Munch, Munch, Yum!";
	}

}
