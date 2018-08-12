package com.travel.agency.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class BikeUtils {
	
	
	public static void printMap(Map<String,String[]> params) {
		String text = params.entrySet()
                .stream()
                .map(e -> e.getKey() + "=\"" + Arrays.toString(e.getValue()) + "\"")
                .collect(Collectors.joining(", "));
		System.out.println(text);
	}
	
	/**
	 * This method returns true if the collection is null or is empty.
	 * @param collection
	 * @return true | false
	 */
	public static boolean isEmpty( Collection<?> collection ){
		if( collection == null || collection.isEmpty() ){
			return true;
		}
		return false;
	}

	/**
	 * This method returns true of the map is null or is empty.
	 * @param map
	 * @return true | false
	 */
	public static boolean isEmpty( Map<?, ?> map ){
		return map == null || map.isEmpty();
	}

	/**
	 * This method returns true if the objet is null.
	 * @param object
	 * @return true | false
	 */
	public static boolean isEmpty( Object object ){
		if( object == null ){
			return true;
		}
		return false;
	}

	/**
	 * This method returns true if the input array is null or its length is zero.
	 * @param array
	 * @return true | false
	 */
	public static boolean isEmpty( Object[] array ){
		if( array == null || array.length == 0 ){
			return true;
		}
		return false;
	}

	/**
	 * This method returns true if the input string is null or its length is zero.
	 * @param  string
	 * @return true | false
	 */
	public static boolean isEmpty( String string ){
		return string == null || string.trim().length() == 0;
	}
	
	public static String getRole(HttpSession session) {
		try {
			String k = (String) session.getAttribute("role");
			if (k.equalsIgnoreCase("admin"))
				return "admin";
			else
				return "user";
		} catch (NullPointerException n) {
			return "user";
		}
	}
	
	public static boolean isLoggedIn(Authentication authentication) {
		return authentication.isAuthenticated();
	}
	
	public static boolean isUser(Authentication authentication) {
		if (!BikeUtils.isEmpty(authentication.getAuthorities())) {
			for (GrantedAuthority g:authentication.getAuthorities()) {
				if (g.getAuthority().equalsIgnoreCase("user")) {
					return true;
				}	
			}
		}
		
		return false;
	}
	
	public static boolean isAdmin(Authentication authentication) {
		if (!BikeUtils.isEmpty(authentication.getAuthorities())) {
			for (GrantedAuthority g:authentication.getAuthorities()) {
				if (g.getAuthority().equalsIgnoreCase("admin")) {
					return true;
				}	
			}
		}
		
		return false;
	}
	
	public static <T> Map<String, Object> convertToHashMap(T obj, String [] rel) throws IllegalArgumentException, IllegalAccessException {

		Map<String, Object> map = new HashMap<>();
		
		// Use MyObject.class.getFields() instead of getDeclaredFields()
		// If you are interested in public fields only
		for (Field field : obj.getClass().getDeclaredFields()) {
			
		    // Skip this if you intend to access to public fields only
		    if (!field.isAccessible()) {
		        field.setAccessible(true);
		    }
		    if (!Arrays.asList(rel).contains(field.getName())) {
		    	map.put(field.getName(), field.get(obj));
		    }
		}
		
		return map;
	}
}
