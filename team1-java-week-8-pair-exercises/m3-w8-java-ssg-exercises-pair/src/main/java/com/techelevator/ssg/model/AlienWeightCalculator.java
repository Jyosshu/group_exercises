package com.techelevator.ssg.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AlienWeightCalculator {

	private String planet;
	private BigDecimal userWeight;
	Map<String, BigDecimal> planetWeightMultiplier;
	
	public AlienWeightCalculator(String planet, BigDecimal userWeight) {
		this.planet = planet;
		this.userWeight = userWeight;
		
		planetWeightMultiplier = new HashMap<String, BigDecimal>();
		planetWeightMultiplier.put("mercury", new BigDecimal(0.37));
		planetWeightMultiplier.put("venus", new BigDecimal(0.90));
		planetWeightMultiplier.put("mars", new BigDecimal(0.38));
		planetWeightMultiplier.put("jupiter", new BigDecimal(2.65));
		planetWeightMultiplier.put("saturn", new BigDecimal(1.13));
		planetWeightMultiplier.put("uranus", new BigDecimal(1.09));
		planetWeightMultiplier.put("neptune", new BigDecimal(1.43));
		planetWeightMultiplier.put("pluto", new BigDecimal(0.04));
	}
	
	public BigDecimal getAlienWeight() {
		BigDecimal metricWeight = userWeight.multiply(new BigDecimal(2.20462));
		BigDecimal alienPlanetWeight = metricWeight.multiply(planetWeightMultiplier.get(planet));
		
		return alienPlanetWeight.divide(new BigDecimal(2.20462));
	}
	
	public String getPlanet() {
		return planet;
	}
	public void setPlanet(String planet) {
		this.planet = planet;
	}
	public BigDecimal getUserWeight() {
		return userWeight;
	}
	public void setUserWeight(BigDecimal userWeight) {
		this.userWeight = userWeight;
	}

	public Map<String, BigDecimal> getPlanetWeightMultiplier() {
		return planetWeightMultiplier;
	}
	
	
}
