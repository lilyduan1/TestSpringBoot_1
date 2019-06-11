package com.lcrew.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;


@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/nflapi")
public class ApiController {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Value("${nflUri}")
	private String nflUri;
	
	@RequestMapping(value = "/currentseason", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getNflCurrentSeason(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		HttpHeaders requestHeaders = new HttpHeaders();
		
		HttpEntity<String> httpRequest = new HttpEntity<String>(requestHeaders);
		ResponseEntity<String> responseEntity;
		responseEntity = restTemplate.exchange(
			nflUri,
			HttpMethod.GET,
			httpRequest,
			String.class
		);
				
		String body = responseEntity.getBody();
		System.out.println("body: " + body);
		return new ResponseEntity<String>(responseEntity.getBody(), HttpStatus.OK);
	}

}
