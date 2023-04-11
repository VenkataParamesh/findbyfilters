package com.example.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entity.RoomEntity;

public interface MeetingRoomRepo extends JpaRepository<RoomEntity, String>,JpaSpecificationExecutor<RoomEntity>{
	
}
	

