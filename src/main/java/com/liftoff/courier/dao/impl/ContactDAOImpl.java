package com.liftoff.courier.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liftoff.courier.dao.BaseDAO;
import com.liftoff.courier.jaxb.Contact;
import com.liftoff.courier.util.PropertyReader;
import com.liftoff.courier.util.ResponseUtils;
import com.mysql.jdbc.Statement;

/**
 * The Class ContactDAOImpl.
 * 
 * @author manoj.n
 * @version 1.0
 */
public class ContactDAOImpl extends BaseDAO {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(ContactDAOImpl.class);
	
	/**
	 * Insert contact.
	 *
	 * @param connection the connection
	 * @param contact the contact
	 * @return the long
	 * @throws SQLException the sQL exception
	 */
	public long insertContact(Connection connection, Contact contact) throws SQLException{
		logger.debug("----- ContactDAOImpl.insertContact() method begins -----");
		PreparedStatement contactStatement = null;
		ResultSet generatedKeys = null;
		long contactId = 0L;
		String exceptionMessage = "Error Occurred in ContactDAOImpl.insertContact()";
		try {
			//Getting the connection from Database
			if(connection != null){
				logger.debug("Contact details insertion into database begins");
				//Query Retrieval From Property Files
				String contactQuery = PropertyReader.getPropertyValue("contact.query.insert");
				
				//Statement Preparation For Contact Insertion
				contactStatement = connection.prepareStatement(contactQuery, Statement.RETURN_GENERATED_KEYS);
				contactStatement.setString(1, contact.getPhoneNumber());
		        contactStatement.setString(2, contact.getEmailId());
		        contactStatement.setString(3, contact.getPhoneNumber());
				
				contactStatement.execute();
				generatedKeys = contactStatement.getGeneratedKeys();
		        if (generatedKeys.next()) {
		        	contactId = generatedKeys.getLong(1);
		        }
		        logger.debug("Contact details insertion into database completed");
			}
			else{
				logger.debug(ResponseUtils.DATABASE_CONNECTION_NULL_RESPONSE);
				return contactId;
			}
		} catch (SQLException exception2) {
			logger.error(ResponseUtils.getExceptionResponse(exceptionMessage,exception2));
			return contactId;
		}
		finally{
			logger.debug("----- ContactDAOImpl.insertContact() method ends -----");
			super.closeStatement(contactStatement);
		}
		return contactId;
	}
	
	/**
	 * Gets the contact.
	 *
	 * @param connection the connection
	 * @param contactId the contact id
	 * @return the contact
	 * @throws SQLException the sQL exception
	 */
	public Contact getContact(Connection connection, long contactId) throws SQLException{
		logger.debug("----- ContactDAOImpl.getContact() method begins -----");
		PreparedStatement contactStatement = null;
		ResultSet resultSet = null;
		Contact contact = null;
		String exceptionMessage = "Error Occurred in ContactDAOImpl.getContact()";
		try {
			//Getting the connection from Database
			if(connection != null){
				logger.debug("Contact details fetch from database begins");
				//Query Retrieval From Property Files
				String contactQuery = PropertyReader.getPropertyValue("contact.query.select");
				
				//Statement Preparation For Contact Insertion
				contactStatement = connection.prepareStatement(contactQuery);
				contactStatement.setLong(1, contactId);
				
				resultSet = contactStatement.executeQuery();
				if (resultSet.next()) {
					resultSet.beforeFirst();
					while(resultSet.next()){
						contact = new Contact();
						contact.setPhoneNumber(resultSet.getString("phone_number"));
						contact.setMobileNumber(resultSet.getString("mobile_number"));
						contact.setEmailId(resultSet.getString("mail_id"));
					}
				} else {
					logger.debug(ResponseUtils.NO_RECORD_IN_DATABASE);
					return null;
				}
				logger.debug("Contact details fetch from database completed");
			}
			else{
				logger.debug(ResponseUtils.DATABASE_CONNECTION_NULL_RESPONSE);
				return contact;
			}
		} catch (SQLException exception2) {
			logger.error(ResponseUtils.getExceptionResponse(exceptionMessage,exception2));
			return contact;
		}
		finally{
			logger.debug("----- ContactDAOImpl.getContact() method ends -----");
			super.closeStatement(contactStatement);
			super.closeResultset(resultSet);
		}
		return contact;
	}
}
