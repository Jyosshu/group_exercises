package com.techelevator;

import java.math.BigDecimal;

public class DrinkItem extends Product {

	public DrinkItem(String name, BigDecimal price) {
		super(name, price);
	}
	
	@Override
	public String getSound() {
		// TODO Auto-generated method stub
		return "Glug Glug, Yum";
	}
	


}
