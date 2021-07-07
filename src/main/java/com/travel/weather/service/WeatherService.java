package com.travel.weather.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.travel.weather.dto.WeatherDataDTO;
import com.travel.weather.entity.WeatherDataEntity;
import com.travel.weather.repository.WeatherRepository;
import com.travel.weather.util.CommonUtil;

@Service
public class WeatherService {
	
	private Logger logger = LoggerFactory.getLogger(WeatherService.class);
	
	
	@Autowired
	private WeatherRepository repository;
	
	public boolean saveWeatherDataService(WeatherDataDTO weatherDataDTO) throws ParseException {
		if(repository.existsById(weatherDataDTO.getId())) {
			logger.info("message=No saveing the data, ID already exists");
			return false;
		}
		WeatherDataEntity weatherDataEntity = CommonUtil.convertToEntity(weatherDataDTO);
		logger.info("message=Saving all data");
		repository.save(weatherDataEntity);
			return true;
	}
 //TODO 
// change this method for new request
	public void saveWeatherDataBulkService(List<WeatherDataDTO> weatherDataDTOs) throws ParseException {
		List<WeatherDataEntity> weatherDataEntities = CommonUtil.convertToEntityList(weatherDataDTOs);
		repository.saveAll(weatherDataEntities);
	}
	
	public List<WeatherDataDTO> getWeatherDataService(String date){
		
		List<WeatherDataDTO> weatherDataDTOs   = new ArrayList<WeatherDataDTO>();
		if ((date != null) && (date.length() > 0)) {
			logger.info("message=Getting values by date, params=date={}", date);
			weatherDataDTOs = CommonUtil.convertToDtoList(repository.findAllByDate(date));
		} else {
			logger.info("message=Getting all data");
			weatherDataDTOs = CommonUtil.convertToDtoList(repository.findAll(Sort.by(Sort.Direction.ASC, "id")));
		}
		return weatherDataDTOs;
	}
	
	public WeatherDataDTO getWeatherDataById(long id){
		logger.info("method=GET, params=id={}", id);
		WeatherDataDTO weatherDataDTO = null;
		Optional<WeatherDataEntity> weatherDataEntity = 
				repository.findById(id);
		if (weatherDataEntity.isPresent()) {
			 weatherDataDTO = CommonUtil.convertToDto(weatherDataEntity.get());
		}
	  
		return weatherDataDTO; 
	}
	
	public void eraseWeatherDataService(){
		logger.info("message=Erasing all");
		repository.deleteAll();
	}
	
	
	
}
