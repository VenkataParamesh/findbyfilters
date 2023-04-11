package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponseDto {
	
	String id;
	String roomno;
	Integer capacity;
	Boolean active;
	private List<FacilityResponseDto> facilities;
}