package com.travel.agency.services;

import java.util.List;

import com.travel.agency.entities.Room;
import com.travel.agency.entities.Service;

public interface RoomService {

    List<Room> findAll();
   Room readById(Integer id);
	
	
	public boolean delete(Room obj);
	
	public Room save(Room obj);
	
	public Room update(Room obj);
	
	public Room findRoomByField(String field);
}
