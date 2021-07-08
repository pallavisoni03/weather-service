package com.travel.weather.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.boot.test.context.SpringBootTest;

import com.travel.weather.dto.WeatherDataDTO;
import com.travel.weather.entity.WeatherDataEntity;
import com.travel.weather.util.CommonUtil;


class CommonUtilTest {
	
	@Test
	public void testForInvalidDate() {
		Assertions.assertThrows(IllegalArgumentException.class, 
				() -> { // implementing the execute method of the Executable interface
					CommonUtil.validateWeatherDate("2021-84-06");
					}, "Exception should be thrown for invalid date"
				);
	}
	
	@Test
	public void testForDtoToEntityConversion() {
		WeatherDataDTO weatherDataDTO = new WeatherDataDTO();
		weatherDataDTO.setId(1l);
		weatherDataDTO.setDate("2021-01-09");
		WeatherDataEntity weatherDataEntity = CommonUtil.convertToEntity(weatherDataDTO);
		Assertions.assertEquals(weatherDataDTO.getId(), weatherDataEntity.getId(), 
				"Problem with converting from DTO to entity");
	}
	

}
