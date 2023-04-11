package com.example.demo.dao;

import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import com.example.demo.repo.MeetingRoomRepo;
import com.example.demo.entity.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@AllArgsConstructor
public class MeetingRoomDao {
	@Autowired
    private final MeetingRoomRepo meetingrepo;
	
	 public List<RoomEntity> findAll() {
		 List<RoomEntity> room=null;
		 try {
			 room=this.meetingrepo.findAll();
			 log.info("Inside in dao to find all the data");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	 return room;
	 
		    
  } 
  public List<RoomEntity> getRooms(Integer capacity,String roomno, Boolean ac, Boolean tv, Boolean board) {
	  List<RoomEntity> entity=null;
	  try {
		  entity=this.meetingrepo.findAll(getRoomSpecification(capacity,roomno, ac, tv, board));		
	} catch (Exception e) {
		log.info(e.getMessage());
	}
      return entity;
  }

  private Specification<RoomEntity> getRoomSpecification(Integer capacity,String roomno, Boolean ac, Boolean tv, Boolean board) {
      return (root, query, cb) -> {
          Predicate predicate = cb.conjunction();
          
          if (capacity!=null) {
              predicate = cb.and(predicate, cb.equal(root.get("capacity"), capacity));
          }
          if(roomno!=null&& !roomno.isEmpty()) {
//        	  predicate =cb.and(predicate,cb.like(root.get("room_no"), room_no ));
        	  predicate =cb.and(predicate,cb.like(cb.lower(root.get("roomno")),"%"+ roomno.toLowerCase()+"%"));
        	 
          }
          
          if (ac != null) {
              predicate = cb.and(predicate, cb.equal(root.join("facilities").get("ac"), ac));
          }
          
          if (tv != null) {
              predicate = cb.and(predicate, cb.equal(root.join("facilities").get("tv"), tv));
          }
          
          if (board != null) {
              predicate = cb.and(predicate, cb.equal(root.join("facilities").get("board"), board));
          }
          
          return predicate;
      };
  }
  
  

  
  
 

}

