package com.example.demo.entity;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;



@Data
@AllArgsConstructor



@NoArgsConstructor
@Entity
@Table(name = "meeting_rooms")
public class RoomEntity {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	String id;
	@Column(name = "roomno")
	String roomno;
	
	@Column(name = "capacity")
	Integer capacity;
	
	@Column(name ="active")
	Boolean active;
	

	@OneToMany(targetEntity = Facility.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "foreign_key", referencedColumnName = "id")
	private List<Facility> facilities;
}