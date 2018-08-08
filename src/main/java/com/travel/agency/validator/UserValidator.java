package com.travel.agency.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.travel.agency.annotation.UniqueUser;
import com.travel.agency.services.UserService;



public class UserValidator implements ConstraintValidator<UniqueUser, String> {   
  
    @Autowired
    private UserService korisnikService;
    
    private String field;
    
    @Override
	public void initialize(UniqueUser valid) {
    	this.field = valid.field();
	}
    
    @Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
    	
    	try {
    		if( korisnikService.findUserByField(value) == null)
        		return true;
		} catch (Exception e) {
			return true;
		}

		return false;
	}   
}
