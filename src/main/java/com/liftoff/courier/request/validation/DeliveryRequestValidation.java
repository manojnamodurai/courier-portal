package com.liftoff.courier.request.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liftoff.courier.jaxb.OrderDetails;
import com.liftoff.courier.util.PropertyReader;
import com.liftoff.courier.util.Validator;

/**
 * The Class DeliveryRequestValidation.
 * 
 * @author manoj.n
 * @version 1.0
 */
public class DeliveryRequestValidation {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(DeliveryRequestValidation.class);
	
	/**
	 * Validate place order request.
	 *
	 * @param orderDetails the order details
	 * @return true, if successful
	 */
	public boolean validatePlaceOrderRequest(OrderDetails orderDetails){
		boolean isValid = false;
		logger.debug("----- DeliveryRequestValidation.validatePlaceOrderRequest() method begins -----");
		try {
			if(Validator.isValidObject(orderDetails)
					&& Validator.isValidObject(orderDetails.getUser())
					&& Validator.isValidString(orderDetails.getUser().getUsername())
					&& Validator.isValidObject(orderDetails.getReceiverDetails())
					&& Validator.isValidObject(orderDetails.getReceiverDetails().getAddress())
					&& Validator.isValidString(orderDetails.getReceiverDetails().getAddress().getName())
					&& Validator.isValidString(orderDetails.getReceiverDetails().getAddress().getStreet1())
					&& Validator.isValidString(orderDetails.getReceiverDetails().getAddress().getCity())
					&& Validator.isValidString(orderDetails.getReceiverDetails().getAddress().getState())
					&& Validator.isValidString(orderDetails.getReceiverDetails().getAddress().getCountry())
					&& Validator.isValidString(orderDetails.getReceiverDetails().getAddress().getZipcode())
					&& Validator.isValidObject(orderDetails.getReceiverDetails().getContact())
					&& Validator.isValidString(orderDetails.getReceiverDetails().getContact().getPhoneNumber())
					&& Validator.isValidObject(orderDetails.getGoodsDetails())
					&& Validator.isValidString(orderDetails.getGoodsDetails().getWeight())
					&& Validator.isValidString(orderDetails.getGoodsDetails().getDescripton())){
				isValid = true;
			} else {
				logger.debug("Invalid request parameters");
			}
		} catch(Exception exception) {
			logger.error("Error occured in DeliveryRequestValidation.validatePlaceOrderRequest() " + exception);
		}
		logger.debug("----- DeliveryRequestValidation.validatePlaceOrderRequest() method ends -----");
		return isValid;
	}
	
	/**
	 * Validate delivery history request.
	 *
	 * @param username the username
	 * @param type the type
	 * @return true, if successful
	 */
	public boolean validateDeliveryHistoryRequest(String username, String type){
		boolean isValid = false;
		String status = PropertyReader.getPropertyValue("list.type.status");
		String history = PropertyReader.getPropertyValue("list.type.history");
		logger.debug("----- DeliveryRequestValidation.validateDeliveryHistoryRequest() method begins -----");
		try {
			if(Validator.isValidString(username)
					&& Validator.isValidString(type)
					&& (status.equalsIgnoreCase(type)
							|| history.equalsIgnoreCase(type))){
				isValid = true;
			} else {
				logger.debug("Invalid request parameters");
			}
		} catch(Exception exception) {
			logger.error("Error occured in DeliveryRequestValidation.validateDeliveryHistoryRequest() " + exception);
		}
		logger.debug("----- DeliveryRequestValidation.validateDeliveryHistoryRequest() method ends -----");
		return isValid;
	}
	
}
