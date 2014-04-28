package com.liftoff.courier.rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liftoff.courier.dao.impl.DeliveryDAOImpl;
import com.liftoff.courier.jaxb.OrderDetails;
import com.liftoff.courier.jaxb.response.OrderDetailsList;
import com.liftoff.courier.jaxb.response.StatusResponseBean;
import com.liftoff.courier.request.validation.DeliveryRequestValidation;
import com.liftoff.courier.util.PropertyReader;
import com.liftoff.courier.util.ResponseUtils;
import com.liftoff.courier.util.Validator;

/**
 * The Class DeliveryService.
 * 
 * @author manoj.n
 * @version 1.0
 */
@Path("/delivery")
public class DeliveryService {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(DeliveryService.class);
	
	/** The request validation. */
	DeliveryRequestValidation requestValidation = null;
	
	/**
	 * Place order.
	 *
	 * @param orderDetails the order details
	 * @return the status response bean
	 */
	@POST
	@Path("/place-order")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public StatusResponseBean placeOrder(OrderDetails orderDetails) {
		logger.debug("----- DeliveryService.placeOrder() method begins -----");
		StatusResponseBean responseBean = null;
		DeliveryDAOImpl deliveryDAOImpl = null;
		boolean isOrderPlaced = false;
		try {
			requestValidation = new DeliveryRequestValidation();
			if(requestValidation.validatePlaceOrderRequest(orderDetails)){
				deliveryDAOImpl = new DeliveryDAOImpl();
				isOrderPlaced = deliveryDAOImpl.insertOrderDetails(orderDetails);
				if(isOrderPlaced){
					responseBean = new StatusResponseBean();
					responseBean.setStatusCode(ResponseUtils.SUCCESS_CODE);
					responseBean.setStatusDescription(ResponseUtils.PLACE_ORDER_SUCCESS_RESPONSE);
				} else {
					responseBean = new StatusResponseBean();
					responseBean.setStatusCode(ResponseUtils.ERROR_CODE);
					responseBean.setStatusDescription(ResponseUtils.PLACE_ORDER_ERROR_RESPONSE);
				}
			} else {
				responseBean = new StatusResponseBean();
				responseBean.setStatusCode(ResponseUtils.ERROR_CODE);
				responseBean.setStatusDescription(ResponseUtils.INVALID_PARAMETERS);
			}
		} catch(Exception exception) {
			logger.error("Error occured in DeliveryService.placeOrder() " + exception);
			responseBean = new StatusResponseBean();
			responseBean.setStatusCode(ResponseUtils.ERROR_CODE);
			responseBean.setStatusDescription(ResponseUtils.PLACE_ORDER_ERROR_RESPONSE);
		}
		logger.debug("----- DeliveryService.placeOrder() method ends -----");
		return responseBean;
	}
	
	/**
	 * Gets the delivery list.
	 *
	 * @param username the username
	 * @param type the type
	 * @return the delivery list
	 */
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public OrderDetailsList getDeliveryList(@QueryParam("username") String username, @QueryParam("type") String type) {
		logger.debug("----- DeliveryService.getDeliveryList() method begins -----");
		DeliveryDAOImpl deliveryDAOImpl = null;
		OrderDetailsList detailsList = null;
		ArrayList<OrderDetails> orderDetailsList = null;
		String status = PropertyReader.getPropertyValue("list.type.status");
		String history = PropertyReader.getPropertyValue("list.type.history");
		String successResponse = null;
		String errorResponse = null;
		try {
			requestValidation = new DeliveryRequestValidation();
			if(requestValidation.validateDeliveryHistoryRequest(username, type)){
				if(history.equalsIgnoreCase(type)){
					successResponse = ResponseUtils.DELIVERY_HISTORY_SUCCESS_RESPONSE;
					errorResponse = ResponseUtils.DELIVERY_HISTORY_ERROR_RESPONSE;
				} else if(status.equalsIgnoreCase(type)){
					successResponse = ResponseUtils.DELIVERY_STATUS_HISTORY_SUCCESS_RESPONSE;
					errorResponse = ResponseUtils.DELIVERY_STATUS_HISTORY_ERROR_RESPONSE;
				}
				deliveryDAOImpl = new DeliveryDAOImpl();
				orderDetailsList = deliveryDAOImpl.getDeliveryList(username,type);
				if(Validator.isValidList(orderDetailsList)){
					detailsList = new OrderDetailsList();
					detailsList.setStatusCode(ResponseUtils.SUCCESS_CODE);
					detailsList.setStatusDescription(successResponse);
					detailsList.setOrderDetails(orderDetailsList);
					detailsList.setType(type);
				} else {
					detailsList = new OrderDetailsList();
					detailsList.setStatusCode(ResponseUtils.NO_RECORD_IN_DATABASE);
					detailsList.setStatusDescription(errorResponse);
				}
			} else {
				detailsList = new OrderDetailsList();
				detailsList.setStatusCode(ResponseUtils.ERROR_CODE);
				detailsList.setStatusDescription(ResponseUtils.INVALID_PARAMETERS);
			}
		} catch(Exception exception) {
			logger.error("Error occured in DeliveryService.getDeliveryList() " + exception);
			detailsList = new OrderDetailsList();
			detailsList.setStatusCode(ResponseUtils.ERROR_CODE);
			detailsList.setStatusDescription(errorResponse);
		}
		logger.debug("----- DeliveryService.getDeliveryList() method ends -----");
		return detailsList;
	}
}
