package com.travel.agency.services;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.agency.dao.GenericDAO;
import com.travel.agency.entities.SubtypeRoom;

@Repository
public class SubtypeRoomServiceImpl implements SubtypeRoomService {
	
	@Autowired
	private GenericDAO<SubtypeRoom> genericDAO;
	
	@Override
	public List<SubtypeRoom> findAll() {
		return genericDAO.findAll(SubtypeRoom.class);
	}

	@Override
	public SubtypeRoom readById(Integer id) {
		return genericDAO.readById(SubtypeRoom.class, "id", id);
	}

	@Override
	public boolean delete(SubtypeRoom obj) {
		return genericDAO.delete(obj);
	}

	@Override
	public SubtypeRoom save(SubtypeRoom obj) {
		return genericDAO.save(obj);
	}

	@Override
	public SubtypeRoom update(SubtypeRoom obj) {
		return genericDAO.update(obj);
	}

	@Override
	public SubtypeRoom findSubtypeRoomByField(String field) {
		try {
			return (SubtypeRoom) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM subtype_rooms INNER JOIN types ON types.id=subtype_rooms.types_id WHERE field=:field", SubtypeRoom.class)
				.setParameter("field", field).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}

}
