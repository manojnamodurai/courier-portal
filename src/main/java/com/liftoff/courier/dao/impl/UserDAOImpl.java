package com.liftoff.courier.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liftoff.courier.dao.BaseDAO;
import com.liftoff.courier.dao.CourierDAO;
import com.liftoff.courier.jaxb.Address;
import com.liftoff.courier.jaxb.Contact;
import com.liftoff.courier.jaxb.request.User;
import com.liftoff.courier.util.PropertyReader;
import com.liftoff.courier.util.ResponseUtils;
import com.liftoff.courier.util.Validator;

/**
 * The Class UserDAOImpl.
 * 
 * @author manoj.n
 * @version 1.0
 */
public class UserDAOImpl extends BaseDAO implements CourierDAO {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	/**
	 * Register user.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws SQLException the sQL exception
	 */
	public boolean registerUser(User user) throws SQLException {
		logger.debug("----- UserDAOImpl.registerUser() method begins -----");
		Connection connection = null;
		PreparedStatement userStatement = null;
		long addressId = 0L;
		long contactId = 0L;
		AddressDAOImpl addressDAOImpl = null;
		ContactDAOImpl contactDAOImpl = null;
		String exceptionMessage = "Error Occurred in UserDAOImpl.registerUser()";
		try {
			//Getting the connection from Database
			connection = super.getConnection();
			if(connection != null){
				logger.debug("User details insertion into database begins");
				
				addressDAOImpl = new AddressDAOImpl();
				addressId = addressDAOImpl.insertAddress(connection, user.getAddress());
				
		        if(Validator.isValidLong(addressId)){
		        	contactDAOImpl = new ContactDAOImpl();
		        	contactId = contactDAOImpl.insertContact(connection, user.getContact());
			        
			        if(Validator.isValidLong(contactId)){
			        	//Query Retrieval From Property Files
						String userQuery = PropertyReader.getPropertyValue("user.query.insert");
						
			        	//Statement Preparation For User Insertion
			        	userStatement = connection.prepareStatement(userQuery);
			        	userStatement.setString(1, user.getUsername());
			        	userStatement.setString(2, user.getPassword());
			        	userStatement.setLong(3, addressId);
			        	userStatement.setLong(4, contactId);
						
						userStatement.execute();
			        } else {
			            throw new SQLException("Creating contact failed, no generated key obtained");
			        }
		        } else {
		            throw new SQLException("Creating address failed, no generated key obtained");
		        }
		        logger.debug("User details insertion into database completed");
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
			logger.debug("----- UserDAOImpl.registerUser() method ends -----");
			super.closeStatement(userStatement);
			super.releaseConnection(connection);
		}
		return true;
	}
	
	/**
	 * Authenticate user.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws SQLException the sQL exception
	 */
	public boolean authenticateUser(User user) throws SQLException{
		logger.debug("----- UserDAOImpl.authenticateUser() method ends -----");
		Connection connection = null;
		PreparedStatement userFetchStatement = null;
		ResultSet resultSet = null;
		String username = null;
		String password = null;
		boolean isValidUser = false;
		String exceptionMessage = "Error Occurred in UserDAOImpl.authenticateUser()";
		try {
			//Getting the connection from Database
			connection = super.getConnection();
			if(connection != null){
				//Query Retrieval From Property Files
				String userFetchQuery = PropertyReader.getPropertyValue("user.query.select1");
				userFetchStatement = connection.prepareStatement(userFetchQuery);
				userFetchStatement.setString(1, user.getUsername());
				userFetchStatement.setString(2, user.getPassword());
				//Execute the Query and Processing The ResultSet
				resultSet = userFetchStatement.executeQuery();
				if (resultSet.next()) {
					resultSet.beforeFirst();
					while(resultSet.next()){
						username = resultSet.getString("username");
						password = resultSet.getString("password");
					}
				} else {
					logger.debug(ResponseUtils.NO_RECORD_IN_DATABASE);
					return isValidUser;
				}
				if(Validator.isValidString(username)
						&& Validator.isValidString(password)){
					isValidUser = true;
				}
			}
			else{
				logger.info(ResponseUtils.DATABASE_CONNECTION_NULL_RESPONSE);
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
			logger.debug("----- UserDAOImpl.authenticateUser() method ends -----");
			super.closeStatement(userFetchStatement);
			super.closeResultset(resultSet);
			super.releaseConnection(connection);
		}
		return isValidUser;
	}
	
	/**
	 * Validate username.
	 *
	 * @param username the username
	 * @return true, if successful
	 * @throws SQLException the sQL exception
	 */
	public boolean validateUsername(String username) throws SQLException{
		logger.debug("----- UserDAOImpl.validateUsername() method ends -----");
		Connection connection = null;
		PreparedStatement userFetchStatement = null;
		ResultSet resultSet = null;
		boolean isValidUsername = true;
		String matchingUsername = null;
		String exceptionMessage = "Error Occurred in UserDAOImpl.validateUsername()";
		try {
			//Getting the connection from Database
			connection = super.getConnection();
			if(connection != null){
				//Query Retrieval From Property Files
				String userFetchQuery = PropertyReader.getPropertyValue("user.query.select2");
				userFetchStatement = connection.prepareStatement(userFetchQuery);
				userFetchStatement.setString(1, username);
				//Execute the Query and Processing The ResultSet
				resultSet = userFetchStatement.executeQuery();
				if (resultSet.next()) {
					resultSet.beforeFirst();
					while(resultSet.next()){
						matchingUsername = resultSet.getString("username");
					}
				} else {
					logger.debug(ResponseUtils.NO_RECORD_IN_DATABASE);
					return isValidUsername;
				}
				if(Validator.isValidString(matchingUsername)){
					isValidUsername = false;
				}
			}
			else{
				logger.info(ResponseUtils.DATABASE_CONNECTION_NULL_RESPONSE);
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
			logger.debug("----- UserDAOImpl.validateUsername() method ends -----");
			super.closeStatement(userFetchStatement);
			super.closeResultset(resultSet);
			super.releaseConnection(connection);
		}
		return isValidUsername;
	}
	
	/**
	 * Gets the user id.
	 *
	 * @param username the username
	 * @return the user id
	 * @throws SQLException the sQL exception
	 */
	public long getUserId(String username) throws SQLException{
		logger.debug("----- UserDAOImpl.getUserId() method ends -----");
		Connection connection = null;
		PreparedStatement userFetchStatement = null;
		ResultSet resultSet = null;
		long userId = 0L;
		String exceptionMessage = "Error Occurred in UserDAOImpl.getUserId()";
		try {
			//Getting the connection from Database
			connection = super.getConnection();
			if(connection != null){
				//Query Retrieval From Property Files
				String userFetchQuery = PropertyReader.getPropertyValue("user.query.select2");
				userFetchStatement = connection.prepareStatement(userFetchQuery);
				userFetchStatement.setString(1, username);
				//Execute the Query and Processing The ResultSet
				resultSet = userFetchStatement.executeQuery();
				if (resultSet.next()) {
					resultSet.beforeFirst();
					while(resultSet.next()){
						userId = resultSet.getLong("id");
					}
				} else {
					logger.debug(ResponseUtils.NO_RECORD_IN_DATABASE);
					return userId;
				}
			}
			else{
				logger.info(ResponseUtils.DATABASE_CONNECTION_NULL_RESPONSE);
				return userId;
			}
		} catch (ClassNotFoundException exception1) {
			logger.error(ResponseUtils.getExceptionResponse(exceptionMessage,exception1));
			return userId;
		} catch (SQLException exception2) {
			logger.error(ResponseUtils.getExceptionResponse(exceptionMessage,exception2));
			return userId;
		}
		finally{
			logger.debug("----- UserDAOImpl.getUserId() method ends -----");
			super.closeStatement(userFetchStatement);
			super.closeResultset(resultSet);
			super.releaseConnection(connection);
		}
		return userId;
	}
	
	/**
	 * Gets the user details.
	 *
	 * @param username the username
	 * @return the user details
	 * @throws SQLException the sQL exception
	 */
	public User getUserDetails(String username) throws SQLException{
		logger.debug("----- UserDAOImpl.getUserDetails() method ends -----");
		Connection connection = null;
		PreparedStatement userFetchStatement = null;
		ResultSet resultSet = null;
		Contact contact = null;
		Address address = null;
		User user = null;
		String exceptionMessage = "Error Occurred in UserDAOImpl.getUserDetails()";
		try {
			//Getting the connection from Database
			connection = super.getConnection();
			if(connection != null){
				//Query Retrieval From Property Files
				String userFetchQuery = PropertyReader.getPropertyValue("user.query.select3");
				userFetchStatement = connection.prepareStatement(userFetchQuery);
				userFetchStatement.setString(1, username);
				//Execute the Query and Processing The ResultSet
				resultSet = userFetchStatement.executeQuery();
				if (resultSet.next()) {
					resultSet.beforeFirst();
					while(resultSet.next()){
						user = new User();
						contact = new Contact();
						address = new Address();
						user.setId(resultSet.getLong("id"));
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
						user.setAddress(address);
						user.setContact(contact);
					}
				} else {
					logger.debug(ResponseUtils.NO_RECORD_IN_DATABASE);
					return null;
				}
			}
			else{
				logger.info(ResponseUtils.DATABASE_CONNECTION_NULL_RESPONSE);
				return user;
			}
		} catch (ClassNotFoundException exception1) {
			logger.error(ResponseUtils.getExceptionResponse(exceptionMessage,exception1));
			return user;
		} catch (SQLException exception2) {
			logger.error(ResponseUtils.getExceptionResponse(exceptionMessage,exception2));
			return user;
		}
		finally{
			logger.debug("----- UserDAOImpl.getUserDetails() method ends -----");
			super.closeStatement(userFetchStatement);
			super.closeResultset(resultSet);
			super.releaseConnection(connection);
		}
		return user;
	}
}
