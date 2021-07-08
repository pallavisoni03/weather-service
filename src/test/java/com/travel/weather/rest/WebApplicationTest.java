package com.travel.weather.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
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

	private static final ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testWorkflow() throws Exception {
		//Create data 
		HttpEntity<String> httpEntity = TestUtil.createJsonRequestFromFile("classpath:input/one.json");
		String expectedResponseStr  = TestUtil.getFileAsString("classpath:expected/one.json");

		ResponseEntity<String> response = 
				restTemplate.postForEntity(serverName + "/weather", httpEntity, String.class);

		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Returned status code does not match");
		Assertions.assertNull(response.getBody());

		// getting all data 
		ResponseEntity<String> getResponse = restTemplate.getForEntity(serverName + "/weather", String.class); 
		Assertions.assertEquals(HttpStatus.OK, getResponse.getStatusCode(), "Returned status code does not match");
		Assertions.assertEquals(mapper.readTree(expectedResponseStr), mapper.readTree(getResponse.getBody()),
				"Returned response body does not match");
		
		// get data by date 
		
		ResponseEntity<String> getDateResponse = restTemplate.getForEntity(serverName + "/weather?date=1986-01-02", String.class); 
		Assertions.assertEquals(HttpStatus.OK, getDateResponse.getStatusCode(), "Returned status code does not match");
		String expectedDateResponseStr  = TestUtil.getFileAsString("classpath:expected/date.json");
		Assertions.assertEquals(mapper.readTree(expectedDateResponseStr), mapper.readTree(getDateResponse.getBody()),
				"Returned response body does not match");
		
		//Erase all data
		restTemplate.delete(serverName + "/erase");
		//checking if data is deleted
		ResponseEntity<String> chkDelResponse = restTemplate.getForEntity(serverName + "/weather", String.class); 
		Assertions.assertEquals(HttpStatus.OK, chkDelResponse.getStatusCode(), "Returned status code does not match");
		Assertions.assertEquals(TestUtil.getFileAsString("classpath:expected/del.json"), chkDelResponse.getBody(), "Data not Erased");
	}

	//@Test
	public void testWorkflowOld() throws Exception {

		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/weather",
				String.class);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "Returned status code does not match");
		Assertions.assertEquals("[]", response.getBody(), "Returned response body does not match");
	}
}
