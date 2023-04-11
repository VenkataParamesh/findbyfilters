package com.example.demo.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacilityResponseDto{

    String id;
    Boolean tv;
    Boolean board;
    Boolean ac;
}