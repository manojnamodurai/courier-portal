package com.liftoff.courier.rest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liftoff.courier.dao.impl.DeliveryDAOImpl;
import com.liftoff.courier.helper.AdminServiceHelper;
import com.liftoff.courier.jaxb.OrderDetails;
import com.liftoff.courier.jaxb.response.OrderDetailsList;
import com.liftoff.courier.request.validation.AdminRequestValidation;
import com.liftoff.courier.util.ResponseUtils;
import com.liftoff.courier.util.Validator;

/**
 * The Class AdminService.
 * 
 * @author manoj.n
 * @version 1.0
 */
@Path("/admin")
public class AdminService {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(AdminService.class);
		
	/** The request validation. */
	AdminRequestValidation requestValidation = null;
	
	/**
	 * Gets the delivery history by period.
	 *
	 * @param period the period
	 * @return the delivery history by period
	 */
	@GET
	@Path("/history")
	@Produces(MediaType.APPLICATION_JSON)
	public OrderDetailsList getDeliveryHistoryByPeriod(@QueryParam("period") String period) {
		logger.debug("----- AdminService.getDeliveryHistoryByPeriod() method begins -----");
		AdminServiceHelper serviceHelper = null;
		OrderDetailsList detailsList = null;
		ArrayList<OrderDetails> orderDetailsList = null;
		try {
			requestValidation = new AdminRequestValidation();
			if(requestValidation.validateHistoryByPeriodRequest(period)){
				serviceHelper = new AdminServiceHelper();
				orderDetailsList = serviceHelper.getDeliveryHistoryByPeriod(period);
				if(Validator.isValidList(orderDetailsList)){
					detailsList = new OrderDetailsList();
					detailsList.setStatusCode(ResponseUtils.SUCCESS_CODE);
					detailsList.setStatusDescription(ResponseUtils.PERIODICAL_HISTORY_SUCCESS_RESPONSE);
					detailsList.setOrderDetails(orderDetailsList);
					detailsList.setPeriod(period);
				} else {
					detailsList = new OrderDetailsList();
					detailsList.setStatusCode(ResponseUtils.ERROR_CODE);
					detailsList.setStatusDescription(ResponseUtils.NO_RECORD_IN_DATABASE);
				}
			} else {
				detailsList = new OrderDetailsList();
				detailsList.setStatusCode(ResponseUtils.ERROR_CODE);
				detailsList.setStatusDescription(ResponseUtils.INVALID_PARAMETERS);
			}
		} catch(Exception exception) {
			logger.error("Error occured in AdminService.getDeliveryHistoryByPeriod() " + exception);
			detailsList = new OrderDetailsList();
			detailsList.setStatusCode(ResponseUtils.ERROR_CODE);
			detailsList.setStatusDescription(ResponseUtils.PERIODICAL_HISTORY_ERROR_RESPONSE);
		}
		logger.debug("----- AdminService.getDeliveryHistoryByPeriod() method ends -----");
		return detailsList;
	}
	
	/**
	 * Gets the delivery list by status.
	 *
	 * @param statusId the status id
	 * @return the delivery list by status
	 */
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public OrderDetailsList getDeliveryListByStatus(@QueryParam("status") String statusId) {
		logger.debug("----- AdminService.getDeliveryListByStatus() method begins -----");
		DeliveryDAOImpl deliveryDAOImpl = null;
		OrderDetailsList detailsList = null;
		ArrayList<OrderDetails> orderDetailsList = null;
		try {
			requestValidation = new AdminRequestValidation();
			if(requestValidation.validateHistoryByStatusRequest(statusId)){
				deliveryDAOImpl = new DeliveryDAOImpl();
				orderDetailsList = deliveryDAOImpl.getDeliveryHistoryByStatus(statusId);
				if(Validator.isValidList(orderDetailsList)){
					detailsList = new OrderDetailsList();
					detailsList.setStatusCode(ResponseUtils.SUCCESS_CODE);
					detailsList.setStatusDescription(ResponseUtils.STATUS_HISTORY_SUCCESS_RESPONSE);
					detailsList.setOrderDetails(orderDetailsList);
				} else {
					detailsList = new OrderDetailsList();
					detailsList.setStatusCode(ResponseUtils.ERROR_CODE);
					detailsList.setStatusDescription(ResponseUtils.NO_RECORD_IN_DATABASE);
				}
			} else {
				detailsList = new OrderDetailsList();
				detailsList.setStatusCode(ResponseUtils.ERROR_CODE);
				detailsList.setStatusDescription(ResponseUtils.INVALID_PARAMETERS);
			}
		} catch(Exception exception) {
			logger.error("Error occured in AdminService.getDeliveryListByStatus() " + exception);
			detailsList = new OrderDetailsList();
			detailsList.setStatusCode(ResponseUtils.ERROR_CODE);
			detailsList.setStatusDescription(ResponseUtils.STATUS_HISTORY_ERROR_RESPONSE);
		}
		logger.debug("----- AdminService.getDeliveryListByStatus() method ends -----");
		return detailsList;
	}

}
