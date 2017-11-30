package com.techelevator.ssg.model;

import java.util.HashMap;
import java.util.Map;

public class AlienTravelCalculator {

	private String planet;
	private String modeTrans;
	private double age;
	private Map<String, Long> distanceToPlanet; 
	private Map<String, Long> speedOfTravel;
	private double yearsToPlanet;
	
	public AlienTravelCalculator(String planet, String modeTrans, double age){
		this.planet = planet;
		this.modeTrans = modeTrans;
		this.age = age;
		
		distanceToPlanet = new HashMap<>();
		distanceToPlanet.put("Mercury", 56974146L);
		distanceToPlanet.put("Venus", 25724767L);
		distanceToPlanet.put("Mars", 48678219L);
		distanceToPlanet.put("Jupiter", 390674710L);
		distanceToPlanet.put("Saturn", 792248270L);
		distanceToPlanet.put("Uranus", 1692662530L);
		distanceToPlanet.put("Neptune", 2703959960L);
		distanceToPlanet.put("Pluto", 4680000000L);
		
		speedOfTravel = new HashMap<>();
		speedOfTravel.put("Walking", 3L);
		speedOfTravel.put("Car", 100L);
		speedOfTravel.put("Bullet Train", 200L);
		speedOfTravel.put("Boeing 747", 570L);
		speedOfTravel.put("Concorde", 1350L);
		
	}
	public double getYearsToPlanet(){
		double distance = (distanceToPlanet.get(getPlanet()) / speedOfTravel.get(getModeTrans()));
		return  (distance / ( 365 * 24));
	}

	public String getPlanet() {
		return planet;
	}
	public void setPlanet(String planet) {
		this.planet = planet;
	}
	public String getModeTrans() {
		return modeTrans;
	}
	public void setModeTrans(String modeTrans) {
		this.modeTrans = modeTrans;
	}
	public double getAge() {
		return age;
	}
	public void setAge(double age) {
		this.age = age;
	}
	
	
}
