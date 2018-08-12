package com.travel.agency.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.travel.agency.entities.Type;
import com.travel.agency.services.TypeService;


@Component
public class TypeConverter implements Converter<String, Type> {

@Autowired
private TypeService typeService;

	@Override
	public Type convert(String arg0) {
	    Integer id = new Integer(arg0);
	    return typeService.readById(id);
	}
}