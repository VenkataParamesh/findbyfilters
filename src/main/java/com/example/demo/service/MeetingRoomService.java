package com.example.demo.service;


import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.*;

import lombok.extern.slf4j.Slf4j;

import com.example.demo.dto.*;
import com.example.demo.dao.MeetingRoomDao;

@Service
@Slf4j
public class MeetingRoomService {
	
  @Autowired
  private final MeetingRoomDao orderDao;
  
  private final ModelMapper modelMapper;

  public MeetingRoomService(MeetingRoomDao orderDao) {
    this.orderDao = orderDao;
    this.modelMapper = new ModelMapper();

    this.modelMapper.createTypeMap(RoomEntity.class, RoomResponseDto.class)
              .addMapping(RoomEntity::getFacilities, RoomResponseDto::setFacilities);
    this.modelMapper.createTypeMap(Facility.class, FacilityResponseDto.class);
  }

  public List<RoomResponseDto> getAllOrders() {
	  List<RoomEntity> orders=null;
	try {
         orders = orderDao.findAll();
         log.info("Inside Service to find all data");
	} catch (Exception e) {
		log.error(e.getMessage());
	}
    return orders.stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  } 
  public List<RoomResponseDto> getRooms(MainRequestDto request) {
	  List<RoomEntity> roomEntities =null;
	  try {
		  Integer capacity=request.getCapacity();
		  String roomno=request.getRoomno();
		  Boolean ac=request.getAc();
		  Boolean tv=request.getTv();
		  Boolean board=request.getBoard();
    	  roomEntities = orderDao.getRooms(capacity,roomno, ac, tv, board);
    	  log.info(" "+roomEntities);
    	  log.info("Indise in service to filter data with capacity");
	} catch (Exception e) {
		log.error(e.getMessage());
	}
	  return roomEntities.stream()
              .map(this::convertToDTO)
              .collect(Collectors.toList()); 
  }
  private RoomResponseDto convertToDTO(RoomEntity entity) {
	  RoomResponseDto roommapper=null;
	  try {
		 // return modelMapper.map(entity, Roomresponsedto.class);
		  roommapper=modelMapper.map(entity, RoomResponseDto.class);
	} catch (Exception e) {
		// TODO: handle exception
	}
	  return roommapper;
  }

}
