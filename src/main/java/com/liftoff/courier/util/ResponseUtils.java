package com.liftoff.courier.util;

/**
 * The Class ResponseUtils.
 * 
 * @author manoj.n
 * @version 1.0
 */
public class ResponseUtils {

	/** The success code. */
	public static String SUCCESS_CODE = "SUCCESS";
	
	/** The error code. */
	public static String ERROR_CODE = "ERROR";
	
	/** The failure code. */
	public static String FAILURE_CODE = "FAILURE";
	
	/** The invalid parameters. */
	public static String INVALID_PARAMETERS = "One / more of the input parameters are invalid";
	
	/** The database connection null response. */
	public static String DATABASE_CONNECTION_NULL_RESPONSE = "Database Connection is Not Available";
	
	/** The database issue response. */
	public static String DATABASE_ISSUE_RESPONSE = "Database Connection Establshment Has Some Issues";
	
	/** The resultset null response. */
	public static String RESULTSET_NULL_RESPONSE = "Resultset is null";
	
	/** The registration success response. */
	public static String REGISTRATION_SUCCESS_RESPONSE = "User successfully registered";
	
	/** The username duplicate response. */
	public static String USERNAME_DUPLICATE_RESPONSE = "Username already taken";
	
	/** The registration error response. */
	public static String REGISTRATION_ERROR_RESPONSE = "Error occured in user registration";
	
	/** The login success response. */
	public static String LOGIN_SUCCESS_RESPONSE = "User aunthentication was successful";
	
	/** The login failure response. */
	public static String LOGIN_FAILURE_RESPONSE = "User aunthentication was not successful";
	
	/** The login error response. */
	public static String LOGIN_ERROR_RESPONSE = "Error occured in user authentication";
	
	/** The place order success response. */
	public static String PLACE_ORDER_SUCCESS_RESPONSE = "Order was placed successfully";
	
	/** The place order error response. */
	public static String PLACE_ORDER_ERROR_RESPONSE = "Error occcured in placing order";
	
	/** The delivery history success response. */
	public static String DELIVERY_HISTORY_SUCCESS_RESPONSE = "History of delivery requests placed by the user fetched successfully";
	
	/** The delivery history error response. */
	public static String DELIVERY_HISTORY_ERROR_RESPONSE = "Error occcured in fetching delivery requests placed by the user";
	
	/** The delivery status history success response. */
	public static String DELIVERY_STATUS_HISTORY_SUCCESS_RESPONSE = "Status of delivery requests placed by the user fetched successfully";
	
	/** The delivery status history error response. */
	public static String DELIVERY_STATUS_HISTORY_ERROR_RESPONSE = "Error occcured in fetching status of delivery requests placed by the user";
	
	/** The periodical history success response. */
	public static String PERIODICAL_HISTORY_SUCCESS_RESPONSE = "Periodical delivery requests fetched successfully";
	
	/** The periodical history error response. */
	public static String PERIODICAL_HISTORY_ERROR_RESPONSE = "Error occcured in fetching delivery requests by period";
	
	/** The status history success response. */
	public static String STATUS_HISTORY_SUCCESS_RESPONSE = "Status based delivery requests fetched successfully";
	
	/** The status history error response. */
	public static String STATUS_HISTORY_ERROR_RESPONSE = "Error occcured in fetching delivery requests by status";
	
	/** The no record in database. */
	public static String NO_RECORD_IN_DATABASE = "No relevant record found in database";
	
	/**
	 * Gets the exception response.
	 *
	 * @param message the message
	 * @param exception the exception
	 * @return the exception response
	 */
	public static String getExceptionResponse(String message, Exception exception){
		return message + " : " + exception.getMessage();
	}
	
}
