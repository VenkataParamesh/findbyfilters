package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainRequestDto {
	
	private Integer capacity;
	private String roomno;
	private Boolean ac;
	private Boolean tv;
	private Boolean board;
	

}
