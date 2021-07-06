package com.travel.weather.rest;

import java.net.URI;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.travel.weather.dto.WeatherDataDTO;
import com.travel.weather.service.WeatherService;
import com.travel.weather.util.CommonUtil;

@RestController

public class WeatherController {

	private Logger logger = LoggerFactory.getLogger(WeatherController.class);
	
	@Autowired
	private WeatherService weatherService;
	
	
	/* Returning either all data or a specific record based on the filter */
	@GetMapping("/weather")
	List<WeatherDataDTO> getWeatherData(@RequestParam(required = false) String date) {
		//TODO
		// put date validation here
		return weatherService.getWeatherDataService(date);
	}
	
	/* getting data by ID */
	@GetMapping("/weather/{id}")
	WeatherDataDTO getWeatherDataByID(@PathVariable(required = true) long id) {
		//TODO
		// put null validation here
		return weatherService.getWeatherDataServiceByID(id);
	}

	/* adding new data */
	@PostMapping("/weather")
	ResponseEntity<String> saveWeatherData(@RequestBody WeatherDataDTO weatherDataDTO) throws ParseException{
		if(!CommonUtil.validateDateFormat(weatherDataDTO.getDate())) {
			return new ResponseEntity<>("Invalid date", HttpStatus.BAD_REQUEST);
		}else {
			weatherService.saveWeatherDataService(weatherDataDTO);
			//TODO
			//Create resource location
	        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
	                                    .path("/{id}")
	                                    .buildAndExpand(weatherDataDTO.getId())
	                                    .toUri();
	        logger.info("method=POST, new_resource_location={}", location); 
			return ResponseEntity.created(location).build();
		}
		
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
