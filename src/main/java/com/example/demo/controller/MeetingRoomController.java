package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.service.MeetingRoomService;
import org.springframework.http.*;

import com.example.demo.dto.*;
import lombok.extern.slf4j.Slf4j;



@RestController
@Slf4j
@CrossOrigin("*")
public class MeetingRoomController {
	
  @Autowired
  private  MeetingRoomService meetingservice;
 
  @Value("${validation-url}")
  String url;
  
  @PostMapping(path="/findbycapacityandfacilities")
  public MainResponseDto getRooms(@RequestBody MainRequestDto request,@RequestHeader("Authorization") String authorization) {
	  MainResponseDto response=new MainResponseDto();
	  List<RoomResponseDto> rooms = null;
	  RestTemplate restTemplate = new RestTemplate();
	  JwtRequestDto jwtRequestDto = new JwtRequestDto();
      jwtRequestDto.setToken(authorization);
      HttpEntity<JwtRequestDto> httpEntity = new HttpEntity<>(jwtRequestDto);
      try {
    	  ResponseEntity<JwtResponseDto> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, JwtResponseDto.class);
    	  if(responseEntity.getBody().getStatus()) {
    		  log.info("Request is validated");
    		  try {
    			  rooms=meetingservice.getRooms(request);
    			  if(!(rooms.isEmpty())) {
    				  response.setResponseCode("00");
    				  response.setResponseDescription("These are the filtered rooms by the user");
    				  response.setRoomEntity(rooms);
    				  response.setTokenStatus("Token is validated Successfully");
    			  }
    			  else {
    				  response.setResponseCode("00");
    				  response.setResponseDescription("These are the filtered rooms by the user");
    				  response.setRoomEntity(rooms);
    				  response.setTokenStatus("Token is validated Successfully");
    			  }
    			
    		    } catch (Exception e) {
    			    response.setResponseCode("01");
    			    response.setResponseDescription(e.getMessage());
    			    response.setRoomEntity(rooms);
    			    response.setTokenStatus("Token is validated Successfully");
    		    } 
    		  
    	  }
    	  else {
    		    response.setResponseCode("01");
			    response.setResponseDescription("Token is not validated please verify it");
			    response.setRoomEntity(rooms);
			    response.setTokenStatus("Token is failed to validate ");
    	  }
	} catch (Exception e) {
		response.setResponseCode("01");
	    response.setResponseDescription(e.getMessage());
	    response.setRoomEntity(rooms);
	    response.setTokenStatus("Token is validated Successfully");
	}
	  
	  return response;
      
  }


 

  

  
}