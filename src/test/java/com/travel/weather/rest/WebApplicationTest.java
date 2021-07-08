package com.travel.weather.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.travel.weather.util.TestUtil;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebApplicationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
    private String serverName;
    
    @BeforeEach
    public void init() {
    	serverName = "http://localhost:" + port;
    }
    
    
    @Test
    public void testWorkflow() throws Exception {
    	HttpEntity<String> httpEntity = TestUtil.createJsonRequestFromFile("classpath:input/one.json");
    	String expectedResponseStr  = TestUtil.getFileAsString("classpath:expected/one.json");
    	
    	ResponseEntity<String> response = 
    			restTemplate.postForEntity(serverName + "/weather", httpEntity, String.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Returned status code does not match");
        Assertions.assertNull(response.getBody());
        
        // getting all data 
        
		  ResponseEntity<String> getResponse = restTemplate.getForEntity(serverName + "/weather", String.class); 
		  Assertions.assertEquals(HttpStatus.OK, getResponse.getStatusCode(), "Returned status code does not match");
		  Assertions.assertEquals(expectedResponseStr, getResponse.getBody(), "Returned response body does not match");
		 
    }

  //@Test
    public void testWorkflowOld() throws Exception {
    	
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/weather",
                String.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "Returned status code does not match");
        Assertions.assertEquals("[]", response.getBody(), "Returned response body does not match");
    }
}
