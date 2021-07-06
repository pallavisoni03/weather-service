package com.travel.weather.util;

import org.apache.commons.validator.GenericValidator;

public class CommonUtil {
	
	public static boolean validateDateFormat(String date) {
		
		return  GenericValidator.isDate(date, "yyyy-MM-dd", true);
		
		 
	}

}
