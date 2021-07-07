package com.travel.weather.util;

import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.commons.validator.GenericValidator;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.travel.weather.dto.WeatherDataDTO;
import com.travel.weather.entity.WeatherDataEntity;
import com.travel.weather.service.WeatherService;


public class CommonUtil {

	private static ModelMapper modelMapper = new ModelMapper();
	
	public static void validateWeatherDataInput(WeatherDataDTO weatherDataDTO) {
		validateWeatherDate(weatherDataDTO.getDate());
		//Ensure ID is not null
		Validate.notNull(weatherDataDTO.getId(), "ID is null");
		Validate.notNull(weatherDataDTO.getLocation().getCity(), "City is null");
		//TODO
		//Change error message
		Validate.inclusiveBetween(-90, 90, weatherDataDTO.getLocation().getLat());
		Validate.inclusiveBetween(-180, 180, weatherDataDTO.getLocation().getLon());
	}

	public static void validateWeatherDate(String date) {

		Validate.isTrue(
				GenericValidator.isDate(
						date, "yyyy-MM-dd", true), "Invalid Date");
	}

	public static WeatherDataDTO convertToDto(WeatherDataEntity weatherDataEntity) {
		WeatherDataDTO weatherDataDTO = modelMapper.map(weatherDataEntity, WeatherDataDTO.class);
		return weatherDataDTO;
	}

	public static WeatherDataEntity convertToEntity(WeatherDataDTO weatherDataDTO) {
		WeatherDataEntity weatherDataEntity = modelMapper.map(weatherDataDTO, WeatherDataEntity.class);
		return weatherDataEntity;
	}

	public static List<WeatherDataDTO> convertToDtoList(List<WeatherDataEntity> weatherDataEntities){
		List<WeatherDataDTO> weatherDataDTOs   = new ArrayList<WeatherDataDTO>();
		for(WeatherDataEntity weatherDataEntity : weatherDataEntities) {
			WeatherDataDTO weatherDataDTO = convertToDto(weatherDataEntity);
			weatherDataDTOs.add(weatherDataDTO);
		}
		return weatherDataDTOs;		
	}

	public static List<WeatherDataEntity> convertToEntityList(List<WeatherDataDTO> weatherDataDTOs) throws ParseException{
		List<WeatherDataEntity> weatherDataEntities   = new ArrayList<WeatherDataEntity>();
		for(WeatherDataDTO weatherDataDTO : weatherDataDTOs) {
			WeatherDataEntity weatherDataEntity = convertToEntity(weatherDataDTO);
			weatherDataEntities.add(weatherDataEntity);
		}
		return weatherDataEntities;	
	}

	public static URI buildUriLocation(String paramName, Long idObject) {
		//logger.info("message=building URI for, params=paramName={}", paramName, "params=idObject={}", idObject);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path(paramName)
				.buildAndExpand(idObject)
				.toUri();

		return location;
	}
}
