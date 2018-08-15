package com.travel.agency.services;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.agency.dao.GenericDAO;
import com.travel.agency.entities.Room;

@Repository
public class RoomServiceImpl implements RoomService {
	
	@Autowired
	private GenericDAO<Room> genericDAO;
	
	@Override
	public List<Room> findAll() {
		return genericDAO.findAll(Room.class);
	}

	@Override
	public Room readById(Integer id) {
		return genericDAO.readById(Room.class, "id", id);
	}

	@Override
	public boolean delete(Room obj) {
		return genericDAO.delete(obj);
	}

	@Override
	public Room save(Room obj) {
		return genericDAO.save(obj);
	}

	@Override
	public Room update(Room obj) {
		return genericDAO.update(obj);
	}

	@Override
	public Room findRoomByField(String field) {
		try {
			return (Room) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM rooms  WHERE field=:field", Room.class)
				.setParameter("field", field).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}

}
