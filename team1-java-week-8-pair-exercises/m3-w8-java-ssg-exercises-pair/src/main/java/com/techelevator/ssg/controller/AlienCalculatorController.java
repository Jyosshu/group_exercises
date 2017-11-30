package com.techelevator.ssg.controller;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.ssg.model.AlienAgeCalculator;
import com.techelevator.ssg.model.AlienTravelCalculator;
import com.techelevator.ssg.model.AlienWeightCalculator;
import com.techelevator.ssg.model.Planet;

@Controller
public class AlienCalculatorController {

	@RequestMapping("/alienWeight")
		public String showAlienWeightCalculatorInput() {
		return "alienWeightInput";
	}
	
	@RequestMapping("/alienWeightResult") 
		public String showAlienWeightCalculatorResults(
			@RequestParam String planet,
			@RequestParam BigDecimal userWeight, 
			ModelMap modelHolder){
		
		AlienWeightCalculator weightResult = new AlienWeightCalculator(planet, userWeight);
		modelHolder.put("alienWeight", weightResult);
		
		return "alienWeightResult";
	}
	
	@RequestMapping ("/alienAge")
	public String ageInput() {
		return "alienAgeInput";
	}
	
	@RequestMapping("/alienAgeResult")
	public String ageResult (@RequestParam String planet, @RequestParam int age, ModelMap modelHolder) {
		AlienAgeCalculator calc = new AlienAgeCalculator(age, planet);
		modelHolder.put("calculator", calc);
		return "alienAgeResult";
	}
	
	@RequestMapping("/alienTravel")
	public String showAlienTravelTimeInputPage(){
			return "alienTravelInput";
		}
	
	@RequestMapping("/alienTravelResult")
	public String showAlienTravelTimeResult(@RequestParam String planet, @RequestParam String modeTrans, @RequestParam double age, ModelMap modelHolder ){
		AlienTravelCalculator calc = new AlienTravelCalculator(planet, modeTrans, age);
		modelHolder.put("travel", calc);
		
		
		return "alienTravelResult";
	}
	
}
