package com.travel.weather.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.weather.entity.WeatherData;
import com.travel.weather.repository.WeatherRepository;

@Service
public class WeatherService {
	
	@Autowired
	private WeatherRepository repository;
	
	public void saveWeatherDataService(WeatherData weatherDataEntity) {
		repository.save(weatherDataEntity);
	}

	public void saveWeatherDataBulkService(List<WeatherData> weatherDataEntities) {
		repository.saveAll(weatherDataEntities);
	}
	
	public List<WeatherData> getWeatherDataService(String date){
		
		if ((date != null) && (date.length() > 0)) {
			return repository.findAllByDate(date);
		} else {
			return repository.findAll();
		}
	}
}
