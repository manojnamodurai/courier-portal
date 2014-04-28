package com.liftoff.courier.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liftoff.courier.dao.BaseDAO;
import com.liftoff.courier.jaxb.Address;
import com.liftoff.courier.jaxb.Contact;
import com.liftoff.courier.jaxb.GoodsDetails;
import com.liftoff.courier.jaxb.OrderDetails;
import java.text.SimpleDateFormat;
import com.liftoff.courier.jaxb.request.User;
import com.liftoff.courier.util.PropertyReader;
import com.liftoff.courier.util.ResponseUtils;
import com.liftoff.courier.util.Validator;

/**
 * The Class DeliveryDAOImpl.
 * 
 * @author manoj.n
 * @version 1.0
 */
public class DeliveryDAOImpl extends BaseDAO {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(DeliveryDAOImpl.class);
	
	/** The Constant STATUS. */
	private static final String STATUS = PropertyReader.getPropertyValue("list.type.status");
	
	/**
	 * Insert order details.
	 *
	 * @param orderDetails the order details
	 * @return true, if successful
	 * @throws SQLException the sQL exception
	 */
	public boolean insertOrderDetails(OrderDetails orderDetails) throws SQLException{

		logger.debug("----- DeliveryDAOImpl.insertOrderDetails() method begins -----");
		Connection connection = null;
		PreparedStatement orderStatement = null;
		long senderId = 0L;
		long addressId = 0L;
		long contactId = 0L;
		long goodsId = 0L;
		AddressDAOImpl addressDAOImpl = null;
		ContactDAOImpl contactDAOImpl = null;
		GoodsDAOImpl goodsDAOImpl = null;
		UserDAOImpl userDAOImpl = null;
		String exceptionMessage = "Error Occurred in DeliveryDAOImpl.insertOrderDetails()";
		try {
			//Getting the connection from Database
			connection = super.getConnection();
			if(connection != null){
				logger.debug("Order details insertion into database begins");
				
				userDAOImpl = new UserDAOImpl();
				senderId = userDAOImpl.getUserId(orderDetails.getUser().getUsername());
				
				if(Validator.isValidLong(senderId)){
					addressDAOImpl = new AddressDAOImpl();
					addressId = addressDAOImpl.insertAddress(connection, orderDetails.getReceiverDetails().getAddress());
					
			        if(Validator.isValidLong(addressId)){
			        	contactDAOImpl = new ContactDAOImpl();
			        	contactId = contactDAOImpl.insertContact(connection, orderDetails.getReceiverDetails().getContact());
				        
				        if(Validator.isValidLong(contactId)){
				        	goodsDAOImpl = new GoodsDAOImpl();
				        	goodsId = goodsDAOImpl.insertGoodsDetails(connection, orderDetails.getGoodsDetails());
				        	
				        	if(Validator.isValidLong(goodsId)){
					        	//Query Retrieval From Property Files
								String orderQuery = PropertyReader.getPropertyValue("order.query.insert");
								
					        	//Statement Preparation For Order Insertion
					        	orderStatement = connection.prepareStatement(orderQuery);
					        	orderStatement.setLong(1, senderId);
					        	orderStatement.setLong(2, contactId);
					        	orderStatement.setLong(3, addressId);
					        	orderStatement.setLong(4, goodsId);
					        	
								orderStatement.execute();
					        } else {
					            throw new SQLException("Creating goods details failed, no generated key obtained");
					        }
				        } else {
				            throw new SQLException("Creating contact failed, no generated key obtained");
				        }
			        } else {
			            throw new SQLException("Creating address failed, no generated key obtained");
			        }
				} else {
		            throw new SQLException("Sender username is invalid, no key obtained");
		        }
		        logger.debug("Order details insertion into database completed");
			}
			else{
				logger.debug(ResponseUtils.DATABASE_CONNECTION_NULL_RESPONSE);
				return false;
			}
		} catch (ClassNotFoundException exception1) {
			logger.error(ResponseUtils.getExceptionResponse(exceptionMessage,exception1));
			return false;
		} catch (SQLException exception2) {
			logger.error(ResponseUtils.getExceptionResponse(exceptionMessage,exception2));
			return false;
		}
		finally{
			logger.debug("----- DeliveryDAOImpl.insertOrderDetails() method ends -----");
			super.closeStatement(orderStatement);
			super.releaseConnection(connection);
		}
		return true;
	}
	
	/**
	 * Gets the delivery list.
	 *
	 * @param username the username
	 * @param type the type
	 * @return the delivery list
	 * @throws SQLException the sQL exception
	 */
	public ArrayList<OrderDetails> getDeliveryList(String username, String type) throws SQLException{
		logger.debug("----- DeliveryDAOImpl.getDeliveryList() method begins -----");
		Connection connection = null;
		PreparedStatement orderFetchStatement = null;
		ResultSet resultSet = null;
		UserDAOImpl userDAOImpl = null;
		ArrayList<OrderDetails> orderDetailsList = null;
		OrderDetails orderDetails = null;
		Address address = null;
		Contact contact = null;
		GoodsDetails goodsDetails = null;
		User senderDetails = null;
		User receiverDetails = null;
		String status = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss:S");
		String exceptionMessage = "Error Occurred in DeliveryDAOImpl.getDeliveryList()";
		try {
			//Getting the connection from Database
			connection = super.getConnection();
			if(connection != null){
				logger.debug("Order list fetch begins");
				
				userDAOImpl = new UserDAOImpl();
				senderDetails = userDAOImpl.getUserDetails(username);
				
	        	//Query Retrieval From Property Files
				String orderQuery = PropertyReader.getPropertyValue("order.query.select1");
				
	        	//Statement Preparation For Order Insertion
	        	orderFetchStatement = connection.prepareStatement(orderQuery);
	        	orderFetchStatement.setLong(1, senderDetails.getId());
	        	
				resultSet = orderFetchStatement.executeQuery();
				orderDetailsList = new ArrayList<OrderDetails>();
				if (resultSet.next()) {
					resultSet.beforeFirst();
					while(resultSet.next()){
						orderDetails = new OrderDetails();
						address = new Address();
						contact = new Contact();
						goodsDetails = new GoodsDetails();
						receiverDetails = new User();
						contact.setPhoneNumber(resultSet.getString("phone"));
						contact.setMobileNumber(resultSet.getString("mobile"));
						contact.setEmailId(resultSet.getString("mail"));
						address.setName(resultSet.getString("name"));
						address.setStreet1(resultSet.getString("street1"));
						address.setStreet2(resultSet.getString("street2"));
						address.setCity(resultSet.getString("city"));
						address.setState(resultSet.getString("state"));
						address.setCountry(resultSet.getString("country"));
						address.setZipcode(resultSet.getString("zipcode"));
						goodsDetails.setWeight(resultSet.getString("weight"));
						goodsDetails.setDescripton(resultSet.getString("description"));
						status = resultSet.getString("status");
						receiverDetails.setAddress(address);
						receiverDetails.setContact(contact);
						orderDetails.setCreatedDate(simpleDateFormat.format(resultSet.getTimestamp("date")));
						if(!STATUS.equalsIgnoreCase(type)){
							orderDetails.setUser(senderDetails);
						}
						orderDetails.setReceiverDetails(receiverDetails);
						orderDetails.setGoodsDetails(goodsDetails);
						orderDetails.setStatus(status);
						orderDetails.setOrderId(resultSet.getLong("id"));
						orderDetailsList.add(orderDetails);
					}
				} else {
					logger.debug(ResponseUtils.NO_RECORD_IN_DATABASE);
					return null;
				}
			} else{
				logger.debug(ResponseUtils.DATABASE_CONNECTION_NULL_RESPONSE);
				return orderDetailsList;
			}
		} catch (ClassNotFoundException exception1) {
			logger.error(ResponseUtils.getExceptionResponse(exceptionMessage,exception1));
			return orderDetailsList;
		} catch (SQLException exception2) {
			logger.error(ResponseUtils.getExceptionResponse(exceptionMessage,exception2));
			return orderDetailsList;
		}
		finally{
			logger.debug("----- DeliveryDAOImpl.getDeliveryList() method ends -----");
			super.closeStatement(orderFetchStatement);
			super.closeResultset(resultSet);
			super.releaseConnection(connection);
		}
		return orderDetailsList;
	}
	
	/**
	 * Gets the delivery history by period.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the delivery history by period
	 * @throws SQLException the sQL exception
	 */
	public ArrayList<OrderDetails> getDeliveryHistoryByPeriod(String startDate, String endDate) throws SQLException{
		logger.debug("----- DeliveryDAOImpl.getDeliveryHistoryByPeriod() method begins -----");
		Connection connection = null;
		PreparedStatement orderFetchStatement = null;
		ResultSet resultSet = null;
		ArrayList<OrderDetails> orderDetailsList = null;
		String exceptionMessage = "Error Occurred in DeliveryDAOImpl.getDeliveryHistoryByPeriod()";
		try {
			//Getting the connection from Database
			connection = super.getConnection();
			if(connection != null){
				logger.debug("Order history fetch begins");
				
	        	//Query Retrieval From Property Files
				String orderQuery = PropertyReader.getPropertyValue("order.query.select2");
				
	        	//Statement Preparation For Order Insertion
	        	orderFetchStatement = connection.prepareStatement(orderQuery);
	        	orderFetchStatement.setString(1, startDate);
	        	orderFetchStatement.setString(2, endDate);
	        	
				resultSet = orderFetchStatement.executeQuery();
				orderDetailsList = constructResponseList(resultSet);
			} else{
				logger.debug(ResponseUtils.DATABASE_CONNECTION_NULL_RESPONSE);
				return orderDetailsList;
			}
		} catch (ClassNotFoundException exception1) {
			logger.error(ResponseUtils.getExceptionResponse(exceptionMessage,exception1));
			return orderDetailsList;
		} catch (SQLException exception2) {
			logger.error(ResponseUtils.getExceptionResponse(exceptionMessage,exception2));
			return orderDetailsList;
		}
		finally{
			logger.debug("----- DeliveryDAOImpl.getDeliveryHistoryByPeriod() method ends -----");
			super.closeStatement(orderFetchStatement);
			super.closeResultset(resultSet);
			super.releaseConnection(connection);
		}
		return orderDetailsList;
	}
	
	/**
	 * Gets the delivery history by status.
	 *
	 * @param statusId the status id
	 * @return the delivery history by status
	 * @throws SQLException the sQL exception
	 */
	public ArrayList<OrderDetails> getDeliveryHistoryByStatus(String statusId) throws SQLException{
		logger.debug("----- DeliveryDAOImpl.getDeliveryHistoryByStatus() method begins -----");
		Connection connection = null;
		PreparedStatement orderFetchStatement = null;
		ResultSet resultSet = null;
		ArrayList<OrderDetails> orderDetailsList = null;
		String exceptionMessage = "Error Occurred in DeliveryDAOImpl.getDeliveryHistoryByStatus()";
		try {
			String[] ids = statusId.split(",");
			//Getting the connection from Database
			connection = super.getConnection();
			if(connection != null){
				logger.debug("Order history fetch begins");
				
	        	//Query Retrieval From Property Files
				String orderQuery = null;
				if(ids.length == 1){
					orderQuery = PropertyReader.getPropertyValue("order.query.select3");
					//Statement Preparation For Order Insertion
		        	orderFetchStatement = connection.prepareStatement(orderQuery);
		        	orderFetchStatement.setLong(1, Long.parseLong(ids[0]));
				} else {
					orderQuery = PropertyReader.getPropertyValue("order.query.select4");
					//Statement Preparation For Order Insertion
		        	orderFetchStatement = connection.prepareStatement(orderQuery);
		        	orderFetchStatement.setLong(1, Long.parseLong(ids[0]));
		        	orderFetchStatement.setLong(2, Long.parseLong(ids[1]));
				}
				
				resultSet = orderFetchStatement.executeQuery();
				orderDetailsList = constructResponseList(resultSet);
			} else{
				logger.debug(ResponseUtils.DATABASE_CONNECTION_NULL_RESPONSE);
				return orderDetailsList;
			}
		} catch (ClassNotFoundException exception1) {
			logger.error(ResponseUtils.getExceptionResponse(exceptionMessage,exception1));
			return orderDetailsList;
		} catch (SQLException exception2) {
			logger.error(ResponseUtils.getExceptionResponse(exceptionMessage,exception2));
			return orderDetailsList;
		}
		finally{
			logger.debug("----- DeliveryDAOImpl.getDeliveryHistoryByStatus() method ends -----");
			super.closeStatement(orderFetchStatement);
			super.closeResultset(resultSet);
			super.releaseConnection(connection);
		}
		return orderDetailsList;
	}
	
	/**
	 * Construct response list.
	 *
	 * @param resultSet the result set
	 * @return the array list
	 */
	private ArrayList<OrderDetails> constructResponseList(ResultSet resultSet){
		logger.debug("----- DeliveryDAOImpl.constructResponseList() method begins -----");
		ArrayList<OrderDetails> orderDetailsList = null;
		OrderDetails orderDetails = null;
		Address address = null;
		Contact contact = null;
		GoodsDetails goodsDetails = null;
		User senderDetails = null;
		String username = null;
		User receiverDetails = null;
		String statusDetail = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss:S");
		try{
			orderDetailsList = new ArrayList<OrderDetails>();
			if (resultSet.next()) {
				resultSet.beforeFirst();
				while(resultSet.next()){
					orderDetails = new OrderDetails();
					address = new Address();
					contact = new Contact();
					goodsDetails = new GoodsDetails();
					senderDetails = new User();
					receiverDetails = new User();
					username = resultSet.getString("username");
					contact.setPhoneNumber(resultSet.getString("phone"));
					contact.setMobileNumber(resultSet.getString("mobile"));
					contact.setEmailId(resultSet.getString("mail"));
					address.setName(resultSet.getString("name"));
					address.setStreet1(resultSet.getString("street1"));
					address.setStreet2(resultSet.getString("street2"));
					address.setCity(resultSet.getString("city"));
					address.setState(resultSet.getString("state"));
					address.setCountry(resultSet.getString("country"));
					address.setZipcode(resultSet.getString("zipcode"));
					goodsDetails.setWeight(resultSet.getString("weight"));
					goodsDetails.setDescripton(resultSet.getString("description"));
					statusDetail = resultSet.getString("status");
					receiverDetails.setAddress(address);
					receiverDetails.setContact(contact);
					orderDetails.setCreatedDate(simpleDateFormat.format(resultSet.getTimestamp("date")));
					orderDetails.setReceiverDetails(receiverDetails);
					orderDetails.setGoodsDetails(goodsDetails);
					orderDetails.setStatus(statusDetail);
					orderDetails.setOrderId(resultSet.getLong("id"));
					if(Validator.isValidString(username)){
						UserDAOImpl userDAOImpl = new UserDAOImpl();
						senderDetails = userDAOImpl.getUserDetails(username);
						orderDetails.setUser(senderDetails);
					}
					orderDetailsList.add(orderDetails);
				}
			} else {
				logger.debug(ResponseUtils.NO_RECORD_IN_DATABASE);
				return null;
			}
		} catch(Exception exception){
			logger.error("Error occured in DeliveryDAOImpl.constructResponseList() method " + exception);
		}
		logger.debug("----- DeliveryDAOImpl.constructResponseList() method ends -----");
		return orderDetailsList;
	}
}
