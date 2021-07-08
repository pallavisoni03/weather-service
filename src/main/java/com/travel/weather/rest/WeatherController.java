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

import com.travel.weather.dto.WeatherDataDTO;
import com.travel.weather.service.WeatherService;
import com.travel.weather.util.CommonUtil;

/** 
 * This class represents the RESt end points.
 */

@RestController
public class WeatherController {

	private Logger logger = LoggerFactory.getLogger(WeatherController.class);

	@Autowired
	private WeatherService weatherService;


	/**
	 *  Returning either all data or a specific record based on the date filter 
	 */
	@GetMapping("/weather")
	ResponseEntity<?> getWeatherData(@RequestParam(required = false) String date) {
		try {
			// Considering a scenario to get all data where date param is null
			if(date!=null) {
				logger.info("message=Validating date to get the data");
				CommonUtil.validateWeatherDate(date);
			}
		}catch(Exception e) {
			logger.error("message=Invalid data", e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		List<WeatherDataDTO> weatherDataDTOs = weatherService.getWeatherDataService(date);
		return ResponseEntity.ok(weatherDataDTOs);

	}

	/**
	 *  Getting data by ID 
	 */
	@GetMapping("/weather/{id}")
	WeatherDataDTO getWeatherDataByID(@PathVariable(required = true) long id) {
		//TODO
		// put null validation here
		return weatherService.getWeatherDataById(id);
	}

	/* 
	 * Adding new data 
	 */
	@PostMapping("/weather")
	ResponseEntity<String> saveWeatherData(@RequestBody WeatherDataDTO weatherDataDTO) throws ParseException{
		//TODO
		// check which exception to catch
		try {
			CommonUtil.validateWeatherDataInput(weatherDataDTO);
		}catch(Exception e) {
			logger.error("message=Invalid data", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		boolean saveFlag = weatherService.saveWeatherDataService(weatherDataDTO);
		if(saveFlag) {
			URI location = CommonUtil.buildUriLocation("/{id}", weatherDataDTO.getId());
			logger.info("method=POST, new_resource_location={}", location); 
			return ResponseEntity.created(location).build();
		}else {
			return new ResponseEntity<>("Id already exixts",HttpStatus.BAD_REQUEST);
		}
	}

	/* 
	 * Adding new data in bulk, for testing
	 */
	@PostMapping("/weather/bulk")
	void saveWeatherDataBulk(@RequestBody List<WeatherDataDTO> weatherDataDTOs) throws ParseException {
		weatherService.saveWeatherDataBulkService(weatherDataDTOs);
	}

	/* 
	 * Erasing all data
	 */
	@DeleteMapping("/erase")
	void eraseWeatherDataBulk() {
		weatherService.eraseWeatherDataService();
	}

}
