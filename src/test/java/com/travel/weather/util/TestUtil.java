package com.travel.weather.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;

public class TestUtil {
	
	public static String getFileAsString(String fileName) {
		try {
			File file = ResourceUtils.getFile(fileName);
			return new String(Files.readAllBytes(file.toPath()));
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	
	public static HttpEntity<String> createJsonRequestFromFile(String fileName) {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        String requestStr = getFileAsString(fileName);
        
        HttpEntity<String> httpEntity = 
    		      new HttpEntity<String>(requestStr, headers);
        
        return httpEntity;
	}
}
