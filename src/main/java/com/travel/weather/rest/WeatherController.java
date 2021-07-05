package com.travel.weather.rest;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.weather.dto.WeatherDataDTO;
import com.travel.weather.service.WeatherService;

@RestController

public class WeatherController {

	private Logger logger = LoggerFactory.getLogger(WeatherController.class);
	
	@Autowired
	private WeatherService weatherService;
	
	
	/* Returning either all data or a specific record based on the filter */
	@GetMapping("/weather")
	List<WeatherDataDTO> getWeatherData(@RequestParam(required = false) String date) {
		return weatherService.getWeatherDataService(date);
	}

	/* adding new data */
	@PostMapping("/weather")
	void saveWeatherData(@RequestBody WeatherDataDTO weatherDataDTO) throws ParseException{
		weatherService.saveWeatherDataService(weatherDataDTO);
		
	}

	@PostMapping("/weather/bulk")
	void saveWeatherDataBulk(@RequestBody List<WeatherDataDTO> weatherDataDTOs) throws ParseException {
		weatherService.saveWeatherDataBulkService(weatherDataDTOs);

	}
	
	@DeleteMapping("/erase")
	void eraseWeatherDataBulk() {
		weatherService.eraseWeatherDataService();
	}
	

}
