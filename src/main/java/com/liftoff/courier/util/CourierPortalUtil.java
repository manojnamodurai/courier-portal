package com.liftoff.courier.util;

import java.sql.Timestamp;
import java.util.Date;

/**
 * The Class CourierPortalUtil.
 * 
 * @author manoj.n
 * @version 1.0
 */
public class CourierPortalUtil {

	/**
	 * Gets the current timestamp.
	 *
	 * @return the current timestamp
	 */
	public static Timestamp getCurrentTimestamp(){
		return new Timestamp(new Date().getTime());
	}
	
}
