package com.travel.agency.services;

import java.util.List;

import com.travel.agency.entities.SubtypeRoom;

public interface SubtypeRoomService {

    List<SubtypeRoom> findAll();
	
	SubtypeRoom readById(Integer id);
	
	public boolean delete(SubtypeRoom obj);
	
	public SubtypeRoom save(SubtypeRoom obj);
	
	public SubtypeRoom update(SubtypeRoom obj);
	
	public SubtypeRoom findSubtypeRoomByField(String field);
}
