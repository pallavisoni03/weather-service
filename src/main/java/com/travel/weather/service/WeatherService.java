package com.travel.weather.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.weather.dto.WeatherDataDTO;
import com.travel.weather.entity.WeatherDataEntity;
import com.travel.weather.repository.WeatherRepository;

@Service
public class WeatherService {
	
	@Autowired
	private WeatherRepository repository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	public void saveWeatherDataService(WeatherDataDTO weatherDataDTO) throws ParseException {
		WeatherDataEntity weatherDataEntity = convertToEntity(weatherDataDTO);
		repository.save(weatherDataEntity);
	}
 
	public void saveWeatherDataBulkService(List<WeatherDataDTO> weatherDataDTOs) throws ParseException {
		List<WeatherDataEntity> WeatherDataEntitys = new ArrayList<WeatherDataEntity>();
		for(WeatherDataDTO weatherDataDTO : weatherDataDTOs) {
			WeatherDataEntitys.add(convertToEntity(weatherDataDTO));
		}
		repository.saveAll(WeatherDataEntitys);
	}
	
	public List<WeatherDataDTO> getWeatherDataService(String date){
		List<WeatherDataDTO> weatherDataDTOs   = new ArrayList<WeatherDataDTO>();
		if ((date != null) && (date.length() > 0)) {
			for(WeatherDataEntity weatherDataEntity : repository.findAllByDate(date)) {
				WeatherDataDTO weatherDataDTO = convertToDto(weatherDataEntity);
				weatherDataDTOs.add(weatherDataDTO);
			}
		} else {

			for(WeatherDataEntity weatherDataEntity : repository.findAll()) {
				WeatherDataDTO weatherDataDTO = convertToDto(weatherDataEntity);
				weatherDataDTOs.add(weatherDataDTO);
	
			
			}
		}
		return weatherDataDTOs;
	}
	
	public void eraseWeatherDataService(){
		repository.deleteAll();
	}
	
	public WeatherDataDTO convertToDto(WeatherDataEntity weatherDataEntity) {
		WeatherDataDTO weatherDataDTO = modelMapper.map(weatherDataEntity, WeatherDataDTO.class);
		return weatherDataDTO;
	}
	
	public WeatherDataEntity convertToEntity(WeatherDataDTO weatherDataDTO) throws ParseException {
		WeatherDataEntity weatherDataEntity = modelMapper.map(weatherDataDTO, WeatherDataEntity.class);
		return weatherDataEntity;
	}
	
}
