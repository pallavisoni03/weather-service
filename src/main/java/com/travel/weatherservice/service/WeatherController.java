package com.travel.weatherservice.service;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.weatherservice.entity.WeatherData;
import com.travel.weatherservice.repository.WeatherRepository;

@RestController
public class WeatherController {

	private final WeatherRepository repository;

	public WeatherController(WeatherRepository repository) {

		this.repository = repository;
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/weather")
	List<WeatherData> allWeatherData(){
		return repository.findAll();
	}

	@PostMapping("/weather1")
	//void saveWeatherData(WeatherData weatherDataEntity){
	void saveWeatherData(String weatherDataEntity){
		repository.save(null);
		
	}
	
	@PostMapping("/weather")
	//void saveWeatherData(WeatherData weatherDataEntity){
	void saveWeatherData1(String weatherDataEntity){
		repository.save(null);
		
	}
}
