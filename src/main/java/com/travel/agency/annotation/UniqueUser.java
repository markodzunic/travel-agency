package com.travel.agency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import javax.validation.Constraint;
import javax.validation.Payload;

import com.travel.agency.validator.UserValidator;



@Constraint(validatedBy = { UserValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUser {
    String message() default "Email is not unique.";
 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
    
    String field() default "";
}
