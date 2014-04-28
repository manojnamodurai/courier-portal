package com.liftoff.courier.request.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liftoff.courier.util.PropertyReader;
import com.liftoff.courier.util.Validator;

/**
 * The Class AdminRequestValidation.
 * 
 * @author manoj.n
 * @version 1.0
 */
public class AdminRequestValidation {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(AdminRequestValidation.class);
	
	/** The Constant DAY. */
	private static final String DAY = PropertyReader.getPropertyValue("history.type.day");
	
	/** The Constant WEEK. */
	private static final String WEEK = PropertyReader.getPropertyValue("history.type.week");
	
	/** The Constant MONTH. */
	private static final String MONTH = PropertyReader.getPropertyValue("history.type.month");
	
	/** The Constant STATUS_ID_1. */
	private static final String STATUS_ID_1 = PropertyReader.getPropertyValue("status.id.1");
	
	/** The Constant STATUS_ID_2. */
	private static final String STATUS_ID_2 = PropertyReader.getPropertyValue("status.id.2");
	
	/** The Constant STATUS_ID_3. */
	private static final String STATUS_ID_3 = PropertyReader.getPropertyValue("status.id.3");
	
	/**
	 * Validate history by period request.
	 *
	 * @param period the period
	 * @return true, if successful
	 */
	public boolean validateHistoryByPeriodRequest(String period){
		boolean isValid = false;
		logger.debug("----- AdminRequestValidation.validateHistoryRequest() method begins -----");
		try {
			if(Validator.isValidString(period)
					&& (DAY.equalsIgnoreCase(period)
							|| WEEK.equalsIgnoreCase(period)
							|| MONTH.equalsIgnoreCase(period))){
				isValid = true;
			} else {
				logger.debug("Invalid request parameters");
			}
		} catch(Exception exception) {
			logger.error("Error occured in AdminRequestValidation.validateHistoryRequest() " + exception);
		}
		logger.debug("----- AdminRequestValidation.validateHistoryRequest() method ends -----");
		return isValid;
	}
	
	/**
	 * Validate history by status request.
	 *
	 * @param statusId the status id
	 * @return true, if successful
	 */
	public boolean validateHistoryByStatusRequest(String statusId){
		boolean isValid = false;
		logger.debug("----- AdminRequestValidation.validateHistoryByStatusRequest() method begins -----");
		try {
			if(Validator.isValidString(statusId)
					&& (STATUS_ID_1.equalsIgnoreCase(statusId)
							|| STATUS_ID_2.equalsIgnoreCase(statusId)
							|| STATUS_ID_3.equalsIgnoreCase(statusId))){
				isValid = true;
			} else {
				logger.debug("Invalid request parameters");
			}
		} catch(Exception exception) {
			logger.error("Error occured in AdminRequestValidation.validateHistoryByStatusRequest() " + exception);
		}
		logger.debug("----- AdminRequestValidation.validateHistoryByStatusRequest() method ends -----");
		return isValid;
	}
}
