package com.liftoff.courier.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * The Class Validator.
 * 
 * @author manoj.n
 * @version 1.0
 */
public final class Validator {

	/**
	 * Checks if is valid string.
	 *
	 * @param value the value
	 * @return true, if is valid string
	 */
	public static boolean isValidString(String value) {
		return (value != null && value.trim().length() > 0);
	}

	/**
	 * Method isValidInt.
	 * @param value int
	 * @return boolean
	 */
	public static boolean isValidInt(int value) {
		return (value != 0);
	}

	/**
	 * Checks if is valid float.
	 *
	 * @param value the value
	 * @return true, if is valid float
	 */
	public static boolean isValidFloat(float value) {
		return (value != 0);
	}
	
	/**
	 * Method isValidLong.
	 * @param value long
	 * @return boolean
	 */
	public static boolean isValidLong(long value) {
		return (value != 0);
	}

	/**
	 * Method isValidObject.
	 * @param object Object
	 * @return boolean
	 */
	public static boolean isValidObject(Object object) {
		return (object != null);
	}

	/**
	 * Method getValidString.
	 * @param param String
	 * @return String
	 */
	public static String getValidString(String param) {
		String value = null;
		if (param != null) {
			value = param.trim();
		}
		return value;
	}

	/**
	 * Method isValidList.
	 * @param values Collection<? extends Object>
	 * @return boolean
	 */
	public static boolean isValidList(Collection<? extends Object> values) {
		return (values != null && values.size() > 0);
	}

	/**
	 * Method isValidSet.
	 * @param values Set<? extends Object>
	 * @return boolean
	 */
	public static boolean isValidSet(Set<? extends Object> values) {
		return (values != null && values.size() > 0);
	}

	/**
	 * Method isValidMap.
	 * @param values Map<? extends Object,? extends Object>
	 * @return boolean
	 */
	public static boolean isValidMap(Map<? extends Object, ? extends Object> values) {
		return (values != null && values.size() > 0);
	}

	/**
	 * Method isNullOrEmpty.
	 * @param inputParam String
	 * @return boolean
	 */
	public static boolean isNullOrEmpty(String inputParam) {
		return inputParam == null || (inputParam != null && inputParam.trim().length() == 0);
	}

	/**
	 * Method isValidStrings.
	 * @param inputParam1 String
	 * @param inputParam2 String
	 * @return boolean
	 */
	public static boolean isValidStrings(String inputParam1, String inputParam2) {
		return inputParam1 != null && inputParam1.trim().length() > 0 && inputParam2 != null && inputParam2.trim().length() > 0;
	}
	
}
