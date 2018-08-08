package com.travel.agency.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.travel.agency.entities.Role;
import com.travel.agency.services.RoleService;

@Component
public class RoleConverter implements Converter<String, Role> {

@Autowired
private RoleService roleService;

	@Override
	public Role convert(String arg0) {
	    Integer id = new Integer(arg0);
	    return roleService.readById(id);
	}
}