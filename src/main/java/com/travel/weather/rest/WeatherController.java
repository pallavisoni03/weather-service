package com.travel.weather.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.weather.entity.WeatherData;
import com.travel.weather.repository.WeatherRepository;
import com.travel.weather.service.WeatherService;

@RestController

public class WeatherController {

	private Logger logger = LoggerFactory.getLogger(WeatherController.class);
	
	@Autowired
	private WeatherService weatherService;
	
	
	/* Returning either all data or a specific record based on the filter */
	@GetMapping("/weather")
	List<WeatherData> getWeatherData(@RequestParam(required = false) String date) {
		
			return weatherService.getWeatherDataService(date);
	}

	/* adding new data */
	@PostMapping("/weather")
	void saveWeatherData(@RequestBody WeatherData weatherDataEntity) {
		weatherService.saveWeatherDataService(weatherDataEntity);
		
	}

	@PostMapping("/weather/bulk")
	void saveWeatherDataBulk(@RequestBody List<WeatherData> weatherDataEntities) {
		weatherService.saveWeatherDataBulkService(weatherDataEntities);

	}

	/* get data by date */
	// @GetMapping("/weather1?date={date}")
	// WeatherData recByDate(@PathVariable String date) {
	// @GetMapping("/weather/{d}")
	/*
	 * @GetMapping("/weather?") WeatherData recByDate(@RequestParam String d) {
	 * logger.info("Value=========={}", d); return repository.findByDate(d); }
	 */

}
