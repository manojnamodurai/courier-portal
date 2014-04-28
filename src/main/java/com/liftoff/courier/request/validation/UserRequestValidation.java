package com.liftoff.courier.request.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liftoff.courier.jaxb.request.User;
import com.liftoff.courier.util.Validator;

/**
 * The Class UserRequestValidation.
 * 
 * @author manoj.n
 * @version 1.0
 */
public class UserRequestValidation {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(UserRequestValidation.class);
	
	/**
	 * Validate registration request.
	 *
	 * @param user the user
	 * @return true, if successful
	 */
	public boolean validateRegistrationRequest(User user){
		boolean isValid = false;
		logger.debug("----- UserRequestValidation.validateRegistrationRequest() method begins -----");
		try {
			if(Validator.isValidObject(user)
					&& Validator.isValidString(user.getUsername())
					&& Validator.isValidString(user.getPassword())
					&& Validator.isValidObject(user.getAddress())
					&& Validator.isValidString(user.getAddress().getName())
					&& Validator.isValidString(user.getAddress().getStreet1())
					&& Validator.isValidString(user.getAddress().getCity())
					&& Validator.isValidString(user.getAddress().getState())
					&& Validator.isValidString(user.getAddress().getCountry())
					&& Validator.isValidString(user.getAddress().getZipcode())
					&& Validator.isValidObject(user.getContact())
					&& Validator.isValidString(user.getContact().getPhoneNumber())){
				isValid = true;
			} else {
				logger.debug("Invalid request parameters");
			}
		} catch(Exception exception) {
			logger.error("Error occured in UserRequestValidation.validateRegistrationRequest() " + exception);
		}
		logger.debug("----- UserRequestValidation.validateRegistrationRequest() method ends -----");
		return isValid;
	}
	
	/**
	 * Validate login request.
	 *
	 * @param user the user
	 * @return true, if successful
	 */
	public boolean validateLoginRequest(User user){
		boolean isValid = false;
		logger.debug("----- UserRequestValidation.validateLoginRequest() method begins -----");
		try {
			if(Validator.isValidObject(user)
					&& Validator.isValidString(user.getUsername())
					&& Validator.isValidString(user.getPassword())){
				isValid = true;
			} else {
				logger.debug("Invalid request parameters");
			}
		} catch(Exception exception) {
			logger.error("Error occured in UserRequestValidation.validateLoginRequest() " + exception);
		}
		logger.debug("----- UserRequestValidation.validateLoginRequest() method ends -----");
		return isValid;
	}
}
