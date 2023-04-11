package com.example.demo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainResponseDto {
	
	private List<RoomResponseDto> roomEntity;
	
	private String responseCode;
	
	private String responseDescription;
	
	private String tokenStatus;
	

}
